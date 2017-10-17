package guide.sql.repository;

import guide.sql.StorageAssert;
import guide.sql.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BasicRepositoryTest extends StorageAssert<Transaction, String> {

    @Autowired
    private TransactionRepository repository;

    @PostConstruct
    @Override
    protected void setup() {
        setRepository(repository);
    }

    private Transaction build() {
        return new Transaction(10, "123456789");
    }

    @Rollback
    @Test
    public void insert() {
        Transaction transaction = build();
        Transaction saved = repository.save(transaction);
        assertNotNull(saved);
        store(saved);
    }

    @Rollback
    @Test
    public void findNotExisted() {
        Transaction transaction = build();
        Transaction found = repository.findOne(transaction.getId());
        assertNull(found);
    }

    @Rollback
    @Test
    public void findExisted() {
        Transaction transaction = build();
        Transaction saved = repository.save(transaction);
        assertNotNull(saved);
        store(saved);

        Transaction found = repository.findOne(saved.getId());
        assertNotNull(found);
    }

    @Rollback
    @Test
    public void removeExisted() {
        Transaction transaction = build();
        Transaction saved = repository.save(transaction);
        assertNotNull(saved);

        repository.delete(saved.getId());

        Transaction found = repository.findOne(saved.getId());
        assertNull(found);
    }
}
