package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllUsers();
    void save(Role role);
    void deleteById(int id);
    Role showUserById(int id);

}
