package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class DataBaseInitializer {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataBaseInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initializeDatabase() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeRoles() {
        roleService.addRole(new Role("ROLE_USER"));
        roleService.addRole(new Role("ROLE_ADMIN"));
    }

    private void initializeUsers() {
        Set<Role> userRole = new HashSet<>();
        userRole.add(roleService.getRoleById(1L));

        Set<Role> adminRole = new HashSet<>();
        adminRole.add(roleService.getRoleById(2L));

        Set<Role> allRoles = new HashSet<>();
        allRoles.add(roleService.getRoleById(1L));
        allRoles.add(roleService.getRoleById(2L));

        userService.saveUser(createUser("Luke", "Skywalker", (byte) 23, "luke.skywalker@gmail.com", "user", "user", userRole));
        userService.saveUser(createUser("John", "Snow", (byte) 31, "john.snow@winterfall.com", "admin", "admin", allRoles));
    }

    private User createUser(String firstName, String lastName, byte age, String email, String username, String password, Set<Role> roles) {
        return new User(firstName, lastName, age, email, username, password, roles);
    }
}



