package com.ufide.homestore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufide.homestore.entity.Product;
import com.ufide.homestore.entity.Sale;
import com.ufide.homestore.entity.SaleDetail;
import com.ufide.homestore.repository.ProductRepository;
import com.ufide.homestore.repository.SaleDetailRepository;
import com.ufide.homestore.repository.SaleRepository;

@Controller
public class VentaController {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;

    public VentaController(
            SaleRepository saleRepository,
            SaleDetailRepository saleDetailRepository,
            ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/ventas") // WIP
    public String listar(Model model) {
        List<Sale> ventas = saleRepository.findAll();
        model.addAttribute("ventas", ventas);
        return "ventas";
    }

    @PostMapping("/ventas/{id}/cancelar")
    public String cancelar(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        Sale venta = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró la venta."));

        if ("CANCELLED".equals(venta.getStatus())) {
            throw new IllegalStateException("Esta venta ya está cancelada.");
        }

        List<SaleDetail> detalles = saleDetailRepository.findBySaleSaleId(id);

        for (SaleDetail detalle : detalles) {
            Product producto = detalle.getProduct();
            producto.setStock(producto.getStock() + detalle.getQuantity());
            productRepository.save(producto);
        }

        venta.setStatus("CANCELLED");
        saleRepository.save(venta);

        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                "Venta cancelada correctamente.");

        return "redirect:/ventas";
    }
}
