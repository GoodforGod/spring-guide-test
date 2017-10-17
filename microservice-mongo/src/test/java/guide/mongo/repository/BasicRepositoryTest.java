package guide.mongo.repository;

import guide.mongo.StorageAssert;
import guide.mongo.config.MongoEmbeddedConfig;
import guide.mongo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { MongoEmbeddedConfig.class })
public class BasicRepositoryTest extends StorageAssert<User, String> {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    @Override
    protected void setup() {
        setRepository(repository);
    }

    private User build() {
        return new User("Bob", "bob@mail.com");
    }

    @Rollback
    @Test
    public void insert() {
        User user = build();
        User saved = repository.save(user);
        assertNotNull(saved);
        store(saved);
    }

    @Rollback
    @Test
    public void findNotExisted() {
        User user = build();
        User found = repository.findOne(user.getId());
        assertNull(found);
    }

    @Rollback
    @Test
    public void findExisted() {
        User user = build();
        User saved = repository.save(user);
        assertNotNull(saved);
        store(saved);

        User found = repository.findOne(saved.getId());
        assertNotNull(found);
    }

    @Rollback
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
