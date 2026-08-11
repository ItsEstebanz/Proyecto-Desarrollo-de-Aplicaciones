package com.ufide.homestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufide.homestore.repository.CategoryRepository;
import com.ufide.homestore.repository.ProductRepository;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public HomeController(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

    @GetMapping("/categories")
    public String categories(Model model) {

        model.addAttribute(
                "categorias",
                categoryRepository.findAll());

        model.addAttribute(
                "productos",
                productRepository.findAll());

        return "categories";
    }

    @GetMapping("/location")
    public String location() {
        return "location";
    }
}
