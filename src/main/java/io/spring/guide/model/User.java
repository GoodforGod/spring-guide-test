package io.spring.guide.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@Document(collection = "users")
public class User extends BasicMongoModel<String> {

    private String name;
    private String email;

    private User() {
        super(UUID.randomUUID().toString());
    }

    public User(String name, String email) {
        this();
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
