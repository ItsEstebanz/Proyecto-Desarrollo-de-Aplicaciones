package com.ufide.homestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Lista de productos por categoria (categoryId)
    List<Product> findByCategory_CategoryId(Integer categoryId);
}
