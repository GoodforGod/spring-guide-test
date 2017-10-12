package io.spring.guide.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
public abstract class BasicMongoModel<ID extends Serializable> extends BasicModel<ID> {

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date updateDate;

    public BasicMongoModel(ID id) {
        super(id);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

}
