package io.spring.guide.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@Document(collection = "users")
public class User extends BasicModel<String> {

    private String name;
    private String email;

    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;

    private User() {
        super(UUID.randomUUID().toString());
    }

    public User(String name, String email) {
        this();
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
