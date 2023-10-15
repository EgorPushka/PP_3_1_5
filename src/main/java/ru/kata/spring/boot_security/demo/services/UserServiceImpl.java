package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO usersRepo;

    @Autowired
    public UserServiceImpl(UserDAO usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> indexUsers() {
        return usersRepo.indexUsers();
    }

    @Override
    @Transactional
    public void add(User user) {
        usersRepo.add(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        usersRepo.delete(id);
    }

    @Override
    @Transactional
    public void edit(User user) {
        usersRepo.edit(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(int id) {
        return usersRepo.getUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String name) {
        return usersRepo.getUserByName(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), mapRolesToAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
