package com.ufide.homestore.controller;

import com.ufide.homestore.entity.Product;
import com.ufide.homestore.repository.CategoryRepository;
import com.ufide.homestore.repository.ProductRepository;
import com.ufide.homestore.repository.SupplierRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductController(ProductRepository productRepository,
            CategoryRepository categoryRepository,
            SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productRepository.findAll());
        return "productos";
    }

    @GetMapping("/inventario")
    public String inventario(Model model) {

        model.addAttribute(
                "productos",
                productRepository.findAll());

        return "inventario";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Product());
        model.addAttribute("categorias", categoryRepository.findAll());
        model.addAttribute("proveedores", supplierRepository.findAll());
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String guardar(
            @Valid @ModelAttribute("producto") Product producto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoryRepository.findAll());
            model.addAttribute("proveedores", supplierRepository.findAll());
            return "producto-form";
        }

        productRepository.save(producto);

        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                producto.getProductId() == null
                        ? "Producto creado correctamente."
                        : "Producto actualizado correctamente.");

        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Product producto = productRepository.findById(id).orElseThrow();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoryRepository.findAll());
        model.addAttribute("proveedores", supplierRepository.findAll());
        return "producto-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productRepository.deleteById(id);
        return "redirect:/productos";
    }
}