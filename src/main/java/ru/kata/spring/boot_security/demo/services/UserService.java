package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User> indexUsers();
    void add(User user);
    void delete(int id);
    void edit(User user);
    User getById(int id);
    User findByUsername(String name);
    UserDetails loadUserByUsername(String username);
}
