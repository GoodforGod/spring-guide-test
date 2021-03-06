package guide.mongo.service.impl;

import guide.mongo.model.BasicMongoModel;
import guide.mongo.service.IBasicModelService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
public abstract class BasicMongoModelService<T extends BasicMongoModel<ID>, ID extends Serializable> implements IBasicModelService<T, ID> {

    private final MongoRepository<T, ID> repository;

    public BasicMongoModelService(MongoRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected boolean isValid(ID id) {
        return (id != null);
    }

    protected boolean isValid(T t) {
        return (t != null);
    }

    protected boolean isValid(List<T> t) {
        return (t != null && !t.isEmpty());
    }

    @Override
    public boolean exist(ID id) {
        return isValid(id) && repository.exists(id);
    }

    @Override
    public boolean exist(T t) {
        return isValid(t) && repository.exists(t.getId());
    }

    @Override
    public T find(ID id) {
        return isValid(id) ? repository.findOne(id) : null;
    }

    @Override
    public List<T> findAll() {
        final Iterable<T> iterable = repository.findAll();
        if(iterable == null || !iterable.iterator().hasNext())
            return Collections.emptyList();

        final List<T> result = new ArrayList<>();
        iterable.iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public T save(T t) {
        return isValid(t) ? repository.save(t) : null;
    }

    @Override
    public List<T> save(List<T> list) {
        if(!isValid(list))
            return Collections.emptyList();

        final Iterable<T> iterable = repository.save(list);
        if(iterable == null || !iterable.iterator().hasNext())
            return Collections.emptyList();

        final List<T> result = new ArrayList<>();
        iterable.iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public void remove(T t) {
        if(!isValid(t))
            return;

        repository.delete(t);
    }

    @Override
    public void remove(ID id) {
        if(!isValid(id))
            return;

        repository.delete(id);
    }

    @Override
    public void remove(List<T> list) {
        if(!isValid(list))
            return;

        repository.delete(list);
    }
}
