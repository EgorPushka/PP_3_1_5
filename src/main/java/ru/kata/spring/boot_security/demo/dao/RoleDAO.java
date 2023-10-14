package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> indexRoles();
    Role getRole(String roleName);
    Role getRoleById(int id);
    void addRole(Role role);

}
