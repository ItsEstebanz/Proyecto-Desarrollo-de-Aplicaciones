package com.ufide.homestore.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufide.homestore.entity.Cart;
import com.ufide.homestore.entity.Sale;
import com.ufide.homestore.entity.User;
import com.ufide.homestore.repository.PaymentMethodRepository;
import com.ufide.homestore.repository.SaleRepository;
import com.ufide.homestore.repository.ShippingMethodRepository;
import com.ufide.homestore.repository.StoreLocationRepository;
import com.ufide.homestore.repository.UserRepository;
import com.ufide.homestore.service.CartService;
import com.ufide.homestore.service.CheckoutService;

@Controller
public class CheckoutController {

        private final CheckoutService checkoutService;
        private final CartService cartService;
        private final UserRepository userRepository;
        private final PaymentMethodRepository paymentMethodRepository;
        private final ShippingMethodRepository shippingMethodRepository;
        private final StoreLocationRepository storeLocationRepository;
        private final SaleRepository saleRepository;

        public CheckoutController(
                        CheckoutService checkoutService,
                        CartService cartService,
                        UserRepository userRepository,
                        PaymentMethodRepository paymentMethodRepository,
                        ShippingMethodRepository shippingMethodRepository,
                        StoreLocationRepository storeLocationRepository,
                        SaleRepository saleRepository) {

                this.checkoutService = checkoutService;
                this.cartService = cartService;
                this.userRepository = userRepository;
                this.paymentMethodRepository = paymentMethodRepository;
                this.shippingMethodRepository = shippingMethodRepository;
                this.storeLocationRepository = storeLocationRepository;
                this.saleRepository = saleRepository;
        }

        @GetMapping("/checkout")
        public String mostrarCheckout(
                        Principal principal,
                        Model model,
                        RedirectAttributes redirectAttributes) {

                if (principal == null) {
                        return "redirect:/login";
                }

                User usuario = userRepository.findByEmail(principal.getName())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "No se encontró el usuario autenticado."));

                Cart carrito = cartService.obtenerCarritoActivo(usuario);

                if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
                        redirectAttributes.addFlashAttribute(
                                        "mensajeError",
                                        "El carrito está vacío.");

                        return "redirect:/cart";
                }

                model.addAttribute("carrito", carrito);

                model.addAttribute(
                                "subtotal",
                                cartService.calcularTotal(carrito.getItems()));

                model.addAttribute(
                                "metodosPago",
                                paymentMethodRepository.findByIsActiveTrue());

                model.addAttribute(
                                "metodosEnvio",
                                shippingMethodRepository.findByIsActiveTrue());

                model.addAttribute(
                                "sucursales",
                                storeLocationRepository.findByIsActiveTrue());

                return "checkout";
        }

        @PostMapping("/checkout/confirmar")
        public String confirmarCompra(
                        Principal principal,
                        @RequestParam Integer paymentMethodId,
                        @RequestParam Integer shippingMethodId,
                        @RequestParam Integer locationId,
                        RedirectAttributes redirectAttributes) {

                if (principal == null) {
                        return "redirect:/login";
                }

                try {
                        Sale venta = checkoutService.confirmarCompra(
                                        principal.getName(),
                                        paymentMethodId,
                                        shippingMethodId,
                                        locationId);

                        redirectAttributes.addFlashAttribute(
                                        "mensajeExito",
                                        "Compra confirmada correctamente.");

                        return "redirect:/compra-exitosa/" + venta.getSaleId();

                } catch (IllegalArgumentException | IllegalStateException e) {

                        redirectAttributes.addFlashAttribute(
                                        "mensajeError",
                                        e.getMessage());

                        return "redirect:/checkout";
                }
        }

        @GetMapping("/compra-exitosa/{id}")
        public String mostrarCompraExitosa(
                        @PathVariable Integer id,
                        Principal principal,
                        Model model) {

                if (principal == null) {
                        return "redirect:/login";
                }

                Sale venta = saleRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "No se encontró la compra."));

                if (venta.getUser() == null
                                || venta.getUser().getEmail() == null
                                || !venta.getUser().getEmail().equals(principal.getName())) {

                        throw new IllegalArgumentException(
                                        "No tienes permiso para ver esta compra.");
                }

                model.addAttribute("venta", venta);

                return "purchase-success";
        }
}