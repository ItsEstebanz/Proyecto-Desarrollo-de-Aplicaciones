package com.ufide.homestore.service;

import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticatorService {

    private final UserRepository userRepository;

    public AuthenticatorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String correo, String contrasena) {
        return userRepository.findByEmail(correo)
                .filter(user -> user.getPassword().equals(contrasena))
                .filter(user -> Boolean.TRUE.equals(user.getIsActive()));
    }
}