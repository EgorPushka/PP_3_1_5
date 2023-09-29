package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @GetMapping(value = "/")
    public String getInformForAll() {
        return "home";
    }

    @GetMapping(value = "/secret")
    public String getSecret(Principal principal) {
        return "secret :: " + principal.getName();
    }

}
