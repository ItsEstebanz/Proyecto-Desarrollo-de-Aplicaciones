package com.ufide.homestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.PaymentMethod;

public interface PaymentMethodRepository
        extends JpaRepository<PaymentMethod, Integer> {

    List<PaymentMethod> findByIsActiveTrue();
}