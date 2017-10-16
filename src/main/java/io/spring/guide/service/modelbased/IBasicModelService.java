package io.spring.guide.service.modelbased;

import io.spring.guide.model.BasicModel;

import java.io.Serializable;
import java.util.List;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
public interface IBasicModelService<T extends BasicModel<ID>, ID extends Serializable> {

    boolean exist(ID id);

    boolean exist(T t);

    T find(ID id);

    List<T> findAll();

    T save(T t);

    List<T> save(List<T> list);

    void remove(T t);

    void remove(ID id);

    void remove(List<T> list);
}
