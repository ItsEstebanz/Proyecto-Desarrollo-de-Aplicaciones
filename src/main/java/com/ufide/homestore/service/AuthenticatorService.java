package com.ufide.homestore.service;

import com.ufide.homestore.entity.Role;
import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthenticatorService
        implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticatorService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        User usuario = userRepository
                .findByEmail(correo)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "No existe un usuario con el correo indicado"
                        )
                );

        Role rol = usuario.getRole();

        String nombreRol = rol.getName();

        if (!nombreRol.startsWith("ROLE_")) {
            nombreRol = "ROLE_" + nombreRol;
        }

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                Boolean.TRUE.equals(usuario.getIsActive()),
                true,
                true,
                true,
                List.of(
                        new SimpleGrantedAuthority(nombreRol)
                )
        );
    }
}