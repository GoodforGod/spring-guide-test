package io.spring.guide.service.modelbased.impl;

import io.spring.guide.model.BasicJpaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
public abstract class BasicJpaModelService<T extends BasicJpaModel<ID>, ID extends Serializable> extends BasicModelService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public BasicJpaModelService(JpaRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }
}
