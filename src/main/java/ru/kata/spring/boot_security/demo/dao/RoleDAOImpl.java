package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> getAllRoles() {
        return entityManager
                .createQuery("select r from Role r", Role.class)
                .getResultList();
    }

    @Override
    public Role getRole(String userRole) {
        return entityManager
                .createQuery("select r from Role r where r.role =: userRole", Role.class)
                .setParameter("userRole", userRole)
                .getSingleResult();
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
