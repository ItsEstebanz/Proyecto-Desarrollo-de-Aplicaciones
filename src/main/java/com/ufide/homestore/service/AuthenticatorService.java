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
public class AuthenticatorService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticatorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        User usuario = userRepository.findByEmail(correo).orElseThrow(() -> new UsernameNotFoundException(
                        "No existe un usuario con el correo indicado"
                ));

        Role rol = usuario.getRole();
        String nombreRol = rol.getName();

        /*
         * Spring Security espera autoridades como ROLE_ADMIN,
         * ROLE_USER, etc.
         *
         * Si la base de datos ya contiene ROLE_ADMIN, se conserva.
         * Si contiene ADMIN, se convierte en ROLE_ADMIN.
         */
        if (!nombreRol.startsWith("ROLE_")) {
            nombreRol = "ROLE_" + nombreRol;
        }

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),

                /*
                 * Las contraseñas actuales están almacenadas como texto normal.
                 * {noop} permite usarlas sin cambiar la base de datos.
                 */
                usuario.getPassword(),

                Boolean.TRUE.equals(usuario.getIsActive()),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority(nombreRol))
        );
    }
}