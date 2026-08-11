package com.ufide.homestore.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.UserRepository;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/inicio")
    public String inicio(Authentication authentication, Model model) {

        User usuario = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new IllegalStateException(
                "El usuario autenticado no existe en la base de datos"));

        model.addAttribute("usuario", usuario);

        return "inicio";
    }
}