package guide.mongo.service.impl;

import guide.mongo.model.User;
import guide.mongo.repository.UserRepository;
import guide.mongo.service.IUserModelService;
import org.springframework.stereotype.Service;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@Service
public class UserService extends BasicMongoModelService<User, String>
                        implements IUserModelService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public User create(String email, String name) {
        return (email != null && name != null)
                ? save(new User(email, name))
                : null;
    }
}
