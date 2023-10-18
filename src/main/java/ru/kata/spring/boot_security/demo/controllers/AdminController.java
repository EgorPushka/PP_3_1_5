package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
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
    public String getSecret(Model model, Principal principal) {

        model.addAttribute("users", userService.indexUsers());
        model.addAttribute("roles", roleRepo.indexRoles());
        model.addAttribute("user", userService.findByUsername(principal.getName()));

        return "/admin/admin";
    }


    @PatchMapping("/admin/users/{id}")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id,
                           @RequestParam(value = "roles", required = false) List<Integer> roleIds) {

        if (!bindingResult.hasErrors()) {
            String rawPassword = user.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encodedPassword);
            List<Role> selectedRoles = roleRepo.getRolesByIds(roleIds);
            user.setRoles(selectedRoles);
            userService.edit(user);
        }
        return REDIRECT_USERS_PAGE;
    }

    @GetMapping("/admin/users/new")
    public String newUserPage(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepo.indexRoles());

        return "/admin/new";
    }

    @PostMapping("/admin/users")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                          @RequestParam(value = "roles", required = false) List<Integer> roleIds) {
        if (bindingResult.hasErrors()) {
            return "/admin/new";
        }
        List<Role> selectedRoles = roleRepo.getRolesByIds(roleIds);
        user.setRoles(selectedRoles);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        userService.add(user);
        return REDIRECT_USERS_PAGE;
    }

    @DeleteMapping("/admin/users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return REDIRECT_USERS_PAGE;
    }

}
