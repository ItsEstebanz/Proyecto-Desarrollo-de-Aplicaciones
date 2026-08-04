package com.ufide.homestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.ShippingMethod;

public interface ShippingMethodRepository
        extends JpaRepository<ShippingMethod, Integer> {

    List<ShippingMethod> findByIsActiveTrue();
}