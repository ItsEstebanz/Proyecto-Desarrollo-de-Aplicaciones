package com.ufide.homestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
