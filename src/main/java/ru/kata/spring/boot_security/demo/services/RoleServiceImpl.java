package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.models.Role;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleRepo;

    @Autowired
    public RoleServiceImpl(RoleDAO roleRepo) {
        this.roleRepo = roleRepo;
    }


    @Override
    public List<Role> indexRoles() {
        return roleRepo.indexRoles();
    }

    @Override
    public Role getRole(String roleName) {
        return roleRepo.getRole(roleName);
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepo.getRoleById(id);
    }

    @Override
    public void addRole(Role role) {
        roleRepo.addRole(role);
    }

    @Override
    public List<Role> getRolesByIds(List<Integer> roleIds) {
        return roleRepo.getRolesByIds(roleIds);
    }
}
