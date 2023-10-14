package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDAO {

    List<User> indexUsers();
    void add(User user);
    void delete(int id);
    void edit(User user);
    User getUserById(int id);
    User getUserByName(String name);

}
