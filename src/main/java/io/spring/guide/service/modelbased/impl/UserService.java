package io.spring.guide.service.modelbased.impl;

import io.spring.guide.model.User;
import io.spring.guide.repository.UserRepository;
import io.spring.guide.service.modelbased.IUserModelService;
import org.springframework.stereotype.Service;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@Service
public class UserService extends BasicModelService<User, String>
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
