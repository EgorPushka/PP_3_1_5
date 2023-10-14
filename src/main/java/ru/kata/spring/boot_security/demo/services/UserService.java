package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {


    List<User> indexUsers();
    void add(User user);
    void delete(int id);
    void edit(User user);
    User getUserById(int id);
    User getUserByName(String name);
}
