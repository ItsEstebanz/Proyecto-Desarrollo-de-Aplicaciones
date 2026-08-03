package com.ufide.homestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                /*
                 * Se protege únicamente /inicio.
                 * Las demás rutas conservan el comportamiento actual.
                 */
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/inicio", "/cart/**").authenticated()
                        .anyRequest().permitAll()
                )

                /*
                 * Spring Security procesa automáticamente el formulario
                 * que actualmente existe en home.html.
                 */
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .usernameParameter("correo")
                        .passwordParameter("contrasena")
                        .defaultSuccessUrl("/productos", true)
                        .failureUrl("/?error=true")
                        .permitAll()
                )

                /*
                 * Se permite cerrar sesión con el enlace GET /logout
                 * que ya existe en inicio.html.
                 */
                .logout(logout -> logout
                        .logoutRequestMatcher(
                                new AntPathRequestMatcher("/logout", "GET")
                        )
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .httpBasic(httpBasic -> httpBasic.disable())

                /*
                 * Se mantiene desactivado porque así estaba el proyecto.
                 * De esta manera no se afectan formularios ni solicitudes
                 * que actualmente funcionan sin token CSRF.
                 */
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}