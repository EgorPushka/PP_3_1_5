package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepo;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {


    private UserService userService;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(UserService userService, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepo = roleRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/users/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/user";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserService userService = (UserService) authentication.getPrincipal();
//        System.out.println(userService.ge

        model.addAttribute("user", new User());

        List<Role> roles = roleRepo.findAll();
        model.addAttribute("roles", roles);

        return "/new";
    }

//    @GetMapping("/users/{id}/edit")
//    public String edit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userServiceImpl.getById(id));
//        return "/edit";
//    }
    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        User user = userService.getById(id);
        model.addAttribute("user", user);

        List<Role> roles = roleRepo.findAll();
        model.addAttribute("roles", roles);

        return "/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {

        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        userService.edit(user);
        return "redirect:/users";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }

        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        userService.add(user);

        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
