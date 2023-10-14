//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.security.Principal;
//
//@Controller
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping(value = "/")
//    public String getInformForAll() {
//        return "/index";
//    }
//
//
//
//    @GetMapping(value = "/user")
//    public String goToUserPage(ModelMap modelMap, Principal principal) {
//        modelMap.addAttribute("user", userService.findByUsername(principal.getName()));
//        return "/user";
//    }
//}
