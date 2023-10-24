package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;
import javax.validation.Valid;
import java.util.List;

public interface UserService {

    void saveUser(User user);

    void updateUser(@Valid User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUserByLogin(String login);

    User getUserById(long id);
}
