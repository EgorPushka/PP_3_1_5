package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAOImpl;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAOImpl userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            return userDAO.getUserByLogin(login);
        } catch (UsernameNotFoundException u) {
            throw new UsernameNotFoundException("user not found : " + login);
        }
    }
}
