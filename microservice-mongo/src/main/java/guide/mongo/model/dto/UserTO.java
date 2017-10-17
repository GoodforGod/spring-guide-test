package guide.mongo.model.dto;


import guide.mongo.model.User;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
public class UserTO {

    private final String id;
    private final String email;
    private final String name;

    private UserTO() {
        this.id = null;
        this.email = null;
        this.name = null;
    }

    private UserTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }

    public static UserTO of(User user) {
        return (user == null)
                ? null
                : new UserTO(user);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTO userTO = (UserTO) o;

        if (id != null ? !id.equals(userTO.id) : userTO.id != null) return false;
        return email != null ? email.equals(userTO.email) : userTO.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
