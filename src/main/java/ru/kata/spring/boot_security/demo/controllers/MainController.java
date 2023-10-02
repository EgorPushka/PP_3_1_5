package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
public class MainController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(value = "/")
    public String getInformForAll() {

        return "/index";
    }

    @GetMapping(value = "/secret")
    public String getSecret(ModelMap modelMap) {

        modelMap.addAttribute("users", userServiceImpl.indexUsers());

        return "/users";
    }

//    @GetMapping("/admins")
//    public String pageForAdmin() {
////        return "ADMIN's Page Here";
//        return "/users";
//    }
//
//    @GetMapping("/managers")
//    public String pageForManager() {
////        return "MANAGER's Page Here";
//        return "/users";
//    }

    @GetMapping("/users/{id}")
    public String getById(@PathVariable("id") int id, Model model) {

        model.addAttribute("user", userServiceImpl.getById(id));

        return "/user";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserService userService = (UserService) authentication.getPrincipal();
//        System.out.println(userService.ge

        User user1 = userServiceImpl.getById(1);
        System.out.println(user1);

        model.addAttribute("user", new User());

        User user2 = userServiceImpl.getById(2);
        System.out.println(user2);
        return "/new";
    }
}
