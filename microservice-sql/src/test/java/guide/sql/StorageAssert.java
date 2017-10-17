package guide.sql;

import guide.sql.model.BasicJpaModel;
import org.junit.After;
import org.junit.Assert;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
public abstract class StorageAssert<T extends BasicJpaModel<ID>, ID extends Serializable> extends Assert {

    private PagingAndSortingRepository<T, ID> repository;

    /**
     * Used to store created objects, to clean up after each test, so you don't have to wipe all database before or after tests
     */
    private final List<T> storage = new ArrayList<>();

    protected abstract void setup();

    protected void setRepository(PagingAndSortingRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected T store(T operation) {
        storage.add(operation);
        return operation;
    }

    protected List<T> store(List<T> operation) {
        storage.addAll(operation);
        return operation;
    }

    @After
    public void clean() {
        repository.delete(storage);
        storage.clear();
    }
}
