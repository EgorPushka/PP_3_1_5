package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.security.Principal;

@Controller

public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "/login";
    }

    @GetMapping("/user")
    public String show(Model model, Principal principal) {
        model.addAttribute("authUser", userService.findByUsername(principal.getName()));
        return "/user";
    }

}
