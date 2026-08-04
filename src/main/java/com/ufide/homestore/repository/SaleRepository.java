package com.ufide.homestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.Sale;

public interface SaleRepository
        extends JpaRepository<Sale, Integer> {

    List<Sale> findByUserUserIdOrderBySaleDateDesc(Integer userId);
}