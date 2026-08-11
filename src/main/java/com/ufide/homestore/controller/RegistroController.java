package com.ufide.homestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufide.homestore.service.UserService;

@Controller
public class RegistroController {

    private final UserService userService;

    public RegistroController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String contrasena,
            RedirectAttributes redirectAttributes) {

        try {
            if (nombre == null || nombre.trim().length() < 3) {
                throw new IllegalArgumentException(
                        "El nombre debe tener al menos 3 caracteres.");
            }

            if (correo == null || correo.isBlank()) {
                throw new IllegalArgumentException(
                        "Debes ingresar un correo electrónico.");
            }

            if (contrasena == null || contrasena.length() < 6) {
                throw new IllegalArgumentException(
                        "La contraseña debe tener al menos 6 caracteres.");
            }

            userService.registrar(nombre, correo, contrasena);

            redirectAttributes.addFlashAttribute(
                    "mensajeExito",
                    "Cuenta creada correctamente. Ya puedes iniciar sesión.");

            return "redirect:/?registroExitoso=true";

        } catch (IllegalArgumentException | IllegalStateException e) {

            redirectAttributes.addFlashAttribute(
                    "mensajeError",
                    e.getMessage());

            return "redirect:/registro";
        }
    }
}