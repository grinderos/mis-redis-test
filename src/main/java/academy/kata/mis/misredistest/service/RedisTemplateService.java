package academy.kata.mis.misredistest.service;

import academy.kata.mis.misredistest.model.User;

import java.util.List;

public interface RedisTemplateService {

    User getUser(Long id);

    List<User> getAllUsers();

    User addUser(Long id, User user);

    User deleteUser(Long id);

    User updateUser(Long id, String name, String lasdstName);
}
