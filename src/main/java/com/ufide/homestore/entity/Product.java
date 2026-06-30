package com.ufide.homestore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * Representa un producto del catálogo. FK hacia category y supplier.
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false, length = 150)
    private String name;

    // "TEXT" permite almacenar descripciones largas, a diferencia de "VARCHAR" que tiene un límite de longitud.
    @Column(columnDefinition = "TEXT")
    private String description;

    // BIGDecimal hace que el precio y el costo sean más precisos que float o double, evitando errores de redondeo.
    // "precision" indica el número total de dígitos y "scale" indica el número de dígitos a la derecha del punto decimal.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costPrice;

    @Column(nullable = false)
    private Integer stock = 0;

    // FK hacia la tabla category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // FK hacia la tabla supplier
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public Product() {}

    public Product(
        String name,
        String description,
        BigDecimal price,
        BigDecimal costPrice,
        Integer stock,
        Category category,
        Supplier supplier
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.costPrice = costPrice;
        this.stock = stock;
        this.category = category;
        this.supplier = supplier;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
