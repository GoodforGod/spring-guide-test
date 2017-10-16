package io.spring.guide.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@MappedSuperclass
public abstract class BasicJpaModel<ID extends Serializable> extends BasicModel<ID> {

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    public BasicJpaModel(ID id) {
        super(id);
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }
}
