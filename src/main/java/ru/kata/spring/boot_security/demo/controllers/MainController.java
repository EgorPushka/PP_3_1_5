package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
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
    public String getSecret(Principal principal, ModelMap modelMap) {
//        User user = userServiceImpl.findByUsername(principal.getName());
        modelMap.addAttribute("users", userServiceImpl.indexUsers());
//        return "secret :: " + user.getUsername() + "  " + user.getUseremail();
        return "/users";
    }


    @GetMapping("/admins")
    public String pageForAdmin() {
//        return "ADMIN's Page Here";
        return "/admin_page";
    }

    @GetMapping("/managers")
    public String pageForManager() {
//        return "MANAGER's Page Here";
        return "/manager_page";
    }


}
