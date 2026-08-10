package com.ufide.homestore.service;

import com.ufide.homestore.entity.Role;
import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.RoleRepository;
import com.ufide.homestore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existeCorreo(String email) {
        return userRepository.findByEmail(email.trim().toLowerCase()).isPresent();
    }

    @Transactional
    public User registrar(String name, String email, String password) {

        String correoNormalizado = email.trim().toLowerCase();

        if (existeCorreo(correoNormalizado)) {
            throw new IllegalArgumentException(
                    "Ya existe una cuenta registrada con ese correo.");
        }

        Role rolCliente = roleRepository.findByName("Comprador")
                .orElseThrow(() -> new IllegalStateException(
                        "No se encontró el rol Comprador en la base de datos."));

        User usuario = new User();
        usuario.setName(name.trim());
        usuario.setEmail(correoNormalizado);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setIsActive(true);
        usuario.setRole(rolCliente);

        return userRepository.save(usuario);
    }
}