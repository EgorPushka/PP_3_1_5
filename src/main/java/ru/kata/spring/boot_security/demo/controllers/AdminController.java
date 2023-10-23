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
@RequestMapping("/admin")
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

    @GetMapping
    public String getSecret(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.indexUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("dbRoles", roleRepo.indexRoles());
        model.addAttribute("principalUser", userService.findByUsername(principal.getName()));
        return "admin";
    }

    @PatchMapping("/{id}")
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

    @PostMapping
    public String addUser(@ModelAttribute("newUser") User user,
                          @RequestParam(value = "roles", required = false) List<Integer> roleIds) {

        List<Role> selectedRoles = roleRepo.getRolesByIds(roleIds);
        user.setRoles(selectedRoles);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        userService.add(user);
        return REDIRECT_USERS_PAGE;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return REDIRECT_USERS_PAGE;
    }

}
