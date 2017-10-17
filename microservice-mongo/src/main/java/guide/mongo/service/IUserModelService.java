package guide.mongo.service;


import guide.mongo.model.User;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
public interface IUserModelService extends IBasicModelService<User, String> {

    User create(String email, String name);
}
