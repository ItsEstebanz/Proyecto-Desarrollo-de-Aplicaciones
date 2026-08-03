package com.ufide.homestore.controller;

import com.ufide.homestore.entity.CartItem;
import com.ufide.homestore.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String verCarrito(
            Authentication authentication,
            Model model
    ) {
        List<CartItem> items = cartService.listarItems(
                authentication.getName()
        );

        model.addAttribute("items", items);
        model.addAttribute("total", cartService.calcularTotal(items)
        );

        return "cart";
    }

    @PostMapping("/agregar/{productId}")
    public String agregar(
            @PathVariable Integer productId,
            @RequestParam(defaultValue = "1") Integer cantidad,
            Authentication authentication,
            RedirectAttributes ra
    ) {
        try {
            cartService.agregarProducto(
                    authentication.getName(),
                    productId,
                    cantidad
            );

            ra.addFlashAttribute(
                    "ok",
                    "Producto agregado al carrito"
            );
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/productos";
    }

    @PostMapping("/items/{itemId}/aumentar")
    public String aumentar(
            @PathVariable Integer itemId,
            Authentication authentication,
            RedirectAttributes ra
    ) {
        try {
            cartService.aumentarCantidad(
                    authentication.getName(),
                    itemId
            );
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/cart";
    }

    @PostMapping("/items/{itemId}/disminuir")
    public String disminuir(
            @PathVariable Integer itemId,
            Authentication authentication,
            RedirectAttributes ra
    ) {
        try { 
            cartService.disminuirCantidad(
                    authentication.getName(),
                    itemId
            );
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/cart";
    }

    @PostMapping("/items/{itemId}/eliminar")
    public String eliminar(
            @PathVariable Integer itemId,
            Authentication authentication,
            RedirectAttributes ra
    ) {
        try {
            cartService.eliminarItem(
                    authentication.getName(),
                    itemId
            );

            ra.addFlashAttribute(
                    "ok",
                    "Producto eliminado del carrito"
            );
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/cart";
    }
}