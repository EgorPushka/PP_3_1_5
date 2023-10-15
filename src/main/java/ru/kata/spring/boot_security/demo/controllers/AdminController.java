package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    private final RoleService roleRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private static final String REDIRECT_USERS_PAGE = "redirect:/admin";

    @Autowired
    public AdminController(UserService userService, RoleService roleRepo, PasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/admin")
    public String getSecret(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.indexUsers());
        return "/users";
    }

    @GetMapping("/users/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/user";
    }



    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        User user = userService.getById(id);
        model.addAttribute("user", user);

        List<Role> roles = roleRepo.indexRoles();
        model.addAttribute("roles", roles);

        return "/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@Valid User user, BindingResult bindingResult, @PathVariable("id") int id,
                           @RequestParam(value = "roles", required = false) List<Integer> roleIds) {

        if (bindingResult.hasErrors()) {
            return "/edit";
        }

        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        List<Role> selectedRoles = roleRepo.getRolesByIds(roleIds);
        user.setRoles(selectedRoles);

        userService.edit(user);
        return REDIRECT_USERS_PAGE;
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        List<Role> roles = roleRepo.indexRoles();
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return "/new";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                          @RequestParam(value = "roles", required = false) List<Integer> roleIds) {

        if (bindingResult.hasErrors()) {
            return "/new";
        }

        List<Role> selectedRoles = roleRepo.getRolesByIds(roleIds);
        user.setRoles(selectedRoles);

        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        userService.add(user);

        return REDIRECT_USERS_PAGE;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return REDIRECT_USERS_PAGE;
    }

}
