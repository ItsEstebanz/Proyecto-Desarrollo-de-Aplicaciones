package com.ufide.homestore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.UserRepository;

@Controller
public class ClienteController {

    private final UserRepository userRepository;

    public ClienteController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/clientes")
    public String clientes(Model model) {

        List<User> clientes = userRepository.findByRole_Name("Comprador");

        model.addAttribute("clientes", clientes);

        return "clientes";
    }
}