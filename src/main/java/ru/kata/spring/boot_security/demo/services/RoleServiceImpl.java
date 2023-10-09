package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAllUsers() {
        return roleRepo.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void deleteById(int id) {
        roleRepo.deleteById(id);
    }

    @Override
    public Role showUserById(int id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        return optionalRole.orElse(null);
    }
}
