package com.ufide.homestore.controller;

import com.ufide.homestore.entity.Product;
import com.ufide.homestore.entity.Sale;
import com.ufide.homestore.entity.SaleDetail;
import com.ufide.homestore.repository.ProductRepository;
import com.ufide.homestore.repository.SaleDetailRepository;
import com.ufide.homestore.repository.SaleRepository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporteController {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public ReporteController(
            ProductRepository productRepository,
            SaleRepository saleRepository,
            SaleDetailRepository saleDetailRepository) {

        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
    }

    @GetMapping("/reportes")
    public String reportes(Model model) {

        List<Product> productos = productRepository.findAll();
        List<Sale> ventas = saleRepository.findAll();
        List<SaleDetail> detalles = saleDetailRepository.findAll();

        long productosAgotados = productos.stream()
                .filter(producto -> producto.getStock() != null
                        && producto.getStock() <= 0)
                .count();

        long productosPocoStock = productos.stream()
                .filter(producto -> producto.getStock() != null
                        && producto.getStock() > 0
                        && producto.getStock() <= 10)
                .count();

        BigDecimal totalVentas = ventas.stream()
                .map(Sale::getTotal)
                .filter(total -> total != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("productos", productos);
        model.addAttribute("ventas", ventas);
        model.addAttribute("detalles", detalles);

        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("productosAgotados", productosAgotados);
        model.addAttribute("productosPocoStock", productosPocoStock);

        model.addAttribute("cantidadVentas", ventas.size());
        model.addAttribute("totalVentas", totalVentas);

        return "reportes";
    }
}