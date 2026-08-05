package com.ufide.homestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.SaleDetail;

public interface SaleDetailRepository
        extends JpaRepository<SaleDetail, Integer> {

    List<SaleDetail> findBySaleSaleId(Integer saleId);
}