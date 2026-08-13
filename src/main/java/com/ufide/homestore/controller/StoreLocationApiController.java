package com.ufide.homestore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufide.homestore.entity.StoreLocation;
import com.ufide.homestore.repository.StoreLocationRepository;


// API REST de sucursales físicas HU-06 pero con Jason

@RestController
@RequestMapping("/api/sucursales") // GET /api/sucursales
public class StoreLocationApiController {

    private final StoreLocationRepository storeLocationRepository;

    public StoreLocationApiController(StoreLocationRepository storeLocationRepository) {
        this.storeLocationRepository = storeLocationRepository;
    }

    // Devuelve todas las sucursales activas (isActive = true)
    @GetMapping
    public List<StoreLocation> listar() {
        return storeLocationRepository.findByIsActiveTrue();
    }
}
