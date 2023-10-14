package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> indexUsers() {
        return entityManager
                .createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String username) {
        return entityManager
                .createQuery("select u from User u where u.username =: name", User.class)
                .setParameter("name", username)
                .getSingleResult();
    }
}
