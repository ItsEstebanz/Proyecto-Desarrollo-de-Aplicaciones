package com.ufide.homestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.homestore.entity.StoreLocation;

public interface StoreLocationRepository
        extends JpaRepository<StoreLocation, Integer> {

    List<StoreLocation> findByIsActiveTrue();
}