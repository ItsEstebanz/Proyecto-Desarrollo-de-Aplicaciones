package com.ufide.homestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/registro",
                                "/nosotros",
                                "/contacto",
                                "/location",
                                "/images/**",
                                "/css/**",
                                "/js/**"
                        )
                        .permitAll()

                        .requestMatchers(
                                "/productos/nuevo",
                                "/productos/guardar",
                                "/productos/editar/**",
                                "/productos/eliminar/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "Supervisor",
                                "Gerente",
                                "Dueno"
                        )

                        .requestMatchers(
                                "/inicio",
                                "/productos/**",
                                "/cart/**",
                                "/checkout/**"
                        )
                        .authenticated()

                        .anyRequest()
                        .permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .usernameParameter("correo")
                        .passwordParameter("contrasena")
                        .defaultSuccessUrl(
                                "/productos",
                                true
                        )
                        .failureUrl("/?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher(
                                        "/logout",
                                        "GET"
                                )
                        )
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .httpBasic(httpBasic ->
                        httpBasic.disable()
                )

                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}