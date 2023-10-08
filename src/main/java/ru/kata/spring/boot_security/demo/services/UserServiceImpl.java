package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepo usersRepo;

    @Autowired
    public UserServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> indexUsers() {
        return usersRepo.findAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        usersRepo.save(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        usersRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void edit(User user) {
        usersRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(int id) {
        return usersRepo.getById(id);
    }

    @Override
    public User findByUsername(String name) {
        return usersRepo.findByUsername(name).get();
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
                .collect(Collectors.toList());
    }
}
