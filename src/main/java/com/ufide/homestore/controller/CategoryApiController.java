package com.ufide.homestore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ufide.homestore.entity.Category;
import com.ufide.homestore.entity.Product;
import com.ufide.homestore.repository.CategoryRepository;
import com.ufide.homestore.repository.ProductRepository;


// API REST de categorías. HU-05, JSON

@RestController
@RequestMapping("/api/categorias")
public class CategoryApiController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryApiController(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Category> listar() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}/productos")
    public List<Product> productosPorCategoria(@PathVariable Integer id) {

        // Verifica que exista la categoría, si no existe lanza ResponseStatusException con HttpStatus.NOT_FOUND (Http Status 404)
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No existe una categoría con id " + id);
        }

        return productRepository.findByCategory_CategoryId(id);
    }
}
