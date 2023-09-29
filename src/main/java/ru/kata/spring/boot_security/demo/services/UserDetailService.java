//package ru.kata.spring.boot_security.demo.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.repositories.UsersRepo;
//import ru.kata.spring.boot_security.demo.security.UserDTO;
//
//import java.util.Optional;
//
//@Service
//public class UserDetailService implements UserDetailsService {
//
//    private final UsersRepo usersRepo;
//
//    @Autowired
//    public UserDetailService(UsersRepo usersRepo) {
//        this.usersRepo = usersRepo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       Optional<User> user = usersRepo.findByUsername(username);
//
//       if (user.isEmpty())
//           throw new UsernameNotFoundException("User not found!");
//
//       return new UserDTO(user.get());
//    }
//}
