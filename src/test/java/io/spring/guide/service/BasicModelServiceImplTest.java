package io.spring.guide.service;

import io.spring.guide.StorageAssert;
import io.spring.guide.config.MongoEmbeddedConfig;
import io.spring.guide.model.User;
import io.spring.guide.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { MongoEmbeddedConfig.class })
public class BasicModelServiceImplTest extends StorageAssert<User, String> {

    @Autowired
    private UserRepository repository;

    @Override
    protected void setup() {
        setRepository(repository);
    }

    private User build() {
        return new User("Bob", "bob@mail.com");
    }

    @Test
    public void insert() {
        User user = build();
        User saved = repository.save(user);
        assertNotNull(saved);
        store(saved);
    }

    @Test
    public void findNotExisted() {
        User user = build();
        User found = repository.findOne(user.getId());
        assertNull(found);
    }

    @Test
    public void findExisted() {
        User user = build();
        User saved = repository.save(user);
        assertNotNull(saved);
        store(saved);

        User found = repository.findOne(saved.getId());
        assertNotNull(found);
    }

    @Test
    public void removeExisted() {
        User user = build();
        User saved = repository.save(user);
        assertNotNull(saved);

        repository.delete(saved.getId());

        User found = repository.findOne(saved.getId());
        assertNull(found);
    }
}
