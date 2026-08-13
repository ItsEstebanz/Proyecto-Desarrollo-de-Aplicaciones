package com.ufide.homestore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.UserRepository;

/**
 * HU-17
 * Eliminar un empleado desactiva su cuenta, en lugar de borrarlo
 * (para no romper las ventas y carritos ya asociados a ese usuario. )
 */

@Controller
public class EmployeeController {

    private final UserRepository userRepository;

    public EmployeeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/empleados") // WIP
    public String listar(Model model) {
        List<User> empleados = userRepository.findByRole_NameNot("Comprador");
        model.addAttribute("empleados", empleados);
        return "empleados";
    }

    @PostMapping("/empleados/{id}/desactivar")
    public String desactivar(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        User empleado = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró el empleado."));

        empleado.setIsActive(false);
        userRepository.save(empleado);

        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                "Empleado desactivado correctamente.");

        return "redirect:/empleados";
    }
}
