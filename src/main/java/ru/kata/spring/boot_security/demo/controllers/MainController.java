package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@RestController
public class MainController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getInformForAll() {
        return "home";
    }

    @GetMapping(value = "/secret")
    public String getSecret(Principal principal) {
        User user = userService.findByUsername(principal.getName());

        return "secret :: " + user.getUsername() + "  " + user.getUseremail();
    }

    @GetMapping("/admins")
    public String pageForAdmin() {
        return "ADMIN's Page Here";
    }

    @GetMapping("/managers")
    public String pageForManager() {
        return "MANAGER's Page Here";
    }

}
