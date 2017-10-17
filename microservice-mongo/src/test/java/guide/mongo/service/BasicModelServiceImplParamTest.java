package guide.mongo.service;

import guide.mongo.StorageAssert;
import guide.mongo.config.MongoEmbeddedConfig;
import guide.mongo.model.User;
import guide.mongo.repository.UserRepository;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import javax.annotation.PostConstruct;
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
public class BasicModelServiceImplParamTest extends StorageAssert<User, String> {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private UserRepository repository;
    private User prop;

    public BasicModelServiceImplParamTest(User prop) {
        this.prop = prop;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new User("Bob", "bob@mail.com") },
                { new User("Tod", "tototo@gmail.com") },
                { new User("Gleb", "doom@mail.ru") },
        });
    }

    @PostConstruct
    @Override
    protected void setup() {
        setRepository(repository);
    }

    @Test
    public void insert() {
        User user = prop;
        User saved = repository.save(user);
        Assert.assertNotNull(saved);
        store(saved);
    }

    @Test
    public void findNotExisted() {
        User user = prop;
        User found = repository.findOne(user.getId());
        Assert.assertNull(found);
    }

    @Test
    public void findExisted() {
        User user = prop;
        User saved = repository.save(user);
        Assert.assertNotNull(saved);
        store(saved);

        User found = repository.findOne(saved.getId());
        Assert.assertNotNull(found);
    }

    @Test
    public void removeExisted() {
        User user = prop;
        User saved = repository.save(user);
        Assert.assertNotNull(saved);

        repository.delete(saved.getId());

        User found = repository.findOne(saved.getId());
        Assert.assertNull(found);
    }
}
