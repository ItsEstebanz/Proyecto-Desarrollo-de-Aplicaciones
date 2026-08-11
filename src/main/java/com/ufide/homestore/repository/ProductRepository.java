package com.ufide.homestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
