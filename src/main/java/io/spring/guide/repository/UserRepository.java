package io.spring.guide.repository;

import io.spring.guide.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
