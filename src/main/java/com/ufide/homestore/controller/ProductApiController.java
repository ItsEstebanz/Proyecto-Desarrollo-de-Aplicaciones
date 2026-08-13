package com.ufide.homestore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ufide.homestore.dto.ProductoRequest;
import com.ufide.homestore.entity.Category;
import com.ufide.homestore.entity.Product;
import com.ufide.homestore.entity.Supplier;
import com.ufide.homestore.repository.CategoryRepository;
import com.ufide.homestore.repository.ProductRepository;
import com.ufide.homestore.repository.SupplierRepository;

import jakarta.validation.Valid;

// API REST de productos. 
// Complementa a ProductController, pero API REST para consumo externo (Postman, apps, etc.)

@RestController
@RequestMapping("/api/productos")
public class ProductApiController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductApiController(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public List<Product> listar() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product obtener(@PathVariable Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No existe un producto con id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product crear(@Valid @RequestBody ProductoRequest request) {

        // Verifica que el id exista en la bd, si no existe lanza ResponseStatusException 404
        Category categoria = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "No existe una categoría con id " + request.categoryId()));
        Supplier proveedor = supplierRepository.findById(request.supplierId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "No existe un proveedor con id " + request.supplierId()));

        Product producto = new Product(
                request.name(),
                request.description(),
                request.price(),
                request.costPrice(),
                request.stock(),
                categoria,
                proveedor);

        return productRepository.save(producto);
    }
}
