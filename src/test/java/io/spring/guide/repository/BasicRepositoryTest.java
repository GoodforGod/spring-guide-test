package io.spring.guide.repository;

import io.spring.guide.StorageAssert;
import io.spring.guide.model.Transaction;
import io.spring.guide.repository.sql.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
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

    @Test
    public void insert() {
        Transaction transaction = build();
        Transaction saved = repository.save(transaction);
        assertNotNull(saved);
        store(saved);
    }

    @Test
    public void findNotExisted() {
        Transaction transaction = build();
        Transaction found = repository.findOne(transaction.getId());
        assertNull(found);
    }

    @Test
    public void findExisted() {
        Transaction transaction = build();
        Transaction saved = repository.save(transaction);
        assertNotNull(saved);
        store(saved);

        Transaction found = repository.findOne(saved.getId());
        assertNotNull(found);
    }

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
