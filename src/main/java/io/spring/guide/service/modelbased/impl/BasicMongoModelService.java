package io.spring.guide.service.modelbased.impl;

import io.spring.guide.model.BasicMongoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
public abstract class BasicMongoModelService<T extends BasicMongoModel<ID>, ID extends Serializable> extends BasicModelService<T, ID> {

    private final MongoRepository<T, ID> repository;

    public BasicMongoModelService(MongoRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }
}
