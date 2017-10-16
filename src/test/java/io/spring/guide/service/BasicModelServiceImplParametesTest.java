package io.spring.guide.service;

import io.spring.guide.StorageAssert;
import io.spring.guide.config.MongoEmbeddedConfig;
import io.spring.guide.model.User;
import io.spring.guide.repository.UserRepository;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.Parameters;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
@RunWith(Parameterized.class)
@ContextConfiguration(classes = { MongoEmbeddedConfig.class })
public class BasicModelServiceImplParametesTest extends StorageAssert<User, String>{

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private UserRepository repository;

    private User prop;

    public BasicModelServiceImplParametesTest(User prop) {
        this.prop = prop;
    }

    @Override
    protected void setup() {
        setRepository(repository);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new User("Bob", "bob@mail.com") },
                { new User("Tod", "tototo@gmail.com") },
                { new User("Gleb", "doom@mail.ru") },
        });
    }

    @Test
    public void insert() {
        User user = prop;
        User saved = repository.save(user);
        assertNotNull(saved);
        store(saved);
    }

    @Test
    public void findNotExisted() {
        User user = prop;
        User found = repository.findOne(user.getId());
        assertNull(found);
    }

    @Test
    public void findExisted() {
        User user = prop;
        User saved = repository.save(user);
        assertNotNull(saved);
        store(saved);

        User found = repository.findOne(saved.getId());
        assertNotNull(found);
    }

    @Test
    public void removeExisted() {
        User user = prop;
        User saved = repository.save(user);
        assertNotNull(saved);

        repository.delete(saved.getId());

        User found = repository.findOne(saved.getId());
        assertNull(found);
    }
}
