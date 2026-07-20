package com.ufide.homestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)
                        throws Exception {

                /*
                 * CONFIGURACIÓN DE SEGURIDAD DEFINITIVA
                 * Se activará cuando login y registro funcionen correctamente.
                 *
                 * http
                 * .authorizeHttpRequests(auth -> auth
                 * .requestMatchers(
                 * "/",
                 * "/inicio",
                 * "/login",
                 * "/registro",
                 * "/error",
                 * "/images/**",
                 * "/css/**",
                 * "/js/**")
                 * .permitAll()
                 * .anyRequest().authenticated())
                 *
                 * .formLogin(form -> form
                 * .loginPage("/inicio")
                 * .loginProcessingUrl("/login")
                 * .usernameParameter("correo")
                 * .passwordParameter("contrasena")
                 * .defaultSuccessUrl("/", true)
                 * .failureUrl("/inicio?error=true")
                 * .permitAll())
                 *
                 * .logout(logout -> logout
                 * .logoutUrl("/logout")
                 * .logoutSuccessUrl("/")
                 * .permitAll());
                 */

                http
                                .authorizeHttpRequests(auth -> auth
                                                .anyRequest().permitAll())
                                .formLogin(form -> form.disable())
                                .httpBasic(httpBasic -> httpBasic.disable())
                                .logout(logout -> logout.disable())
                                .csrf(csrf -> csrf.disable());

                return http.build();
        }
}