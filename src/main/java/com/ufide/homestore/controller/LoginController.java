package com.ufide.homestore.controller;

import com.ufide.homestore.entity.User;
import com.ufide.homestore.service.AuthenticatorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//.optional es para evitar que si un valor no existe, no genere un error y se pueda manejar de manera más segura.
import java.util.Optional;

@Controller
public class LoginController {

    private final AuthenticatorService authenticatorService;

    public LoginController(AuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("correo") String correo,
                        @RequestParam("contrasena") String contrasena,
                        HttpSession session,
                        Model model) {

        Optional<User> usuario = authenticatorService.login(correo, contrasena);

        if (usuario.isPresent()) {
            session.setAttribute("usuarioLogueado", usuario.get());
            return "redirect:/inicio";
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "home";
    }

    @GetMapping("/inicio")
    public String inicio(HttpSession session, Model model) {

        User usuario = (User) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);

        return "inicio";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
