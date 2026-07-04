package com.ufide.homestore.controller;

import com.ufide.homestore.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam("correo") String correo,
                        @RequestParam("contrasena") String contrasena) {

        return userRepository.findAll().stream()
                .anyMatch(user -> user.getEmail().equals(correo)
                        && user.getPassword().equals(contrasena)
                        && user.getIsActive())
                ? "redirect:/productos"
                : "redirect:/";
    }
}