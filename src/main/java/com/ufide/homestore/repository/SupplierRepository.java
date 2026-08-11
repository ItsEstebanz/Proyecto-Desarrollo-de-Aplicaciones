package com.ufide.homestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
