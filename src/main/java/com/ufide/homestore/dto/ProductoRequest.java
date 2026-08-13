package com.ufide.homestore.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


// Cuerpo esperado para crear un producto vía POST /api/productos.
// Recibe los IDs de categoría/proveedor
// Simplifica trabajo en Postman luego

public record ProductoRequest(

        @NotBlank(message = "El nombre es obligatorio")
        String name,

        String description,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
        BigDecimal price,

        @NotNull(message = "El costo es obligatorio")
        @DecimalMin(value = "0.00", message = "El costo no puede ser negativo")
        BigDecimal costPrice,

        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,

        @NotNull(message = "Debe indicar el categoryId")
        Integer categoryId,

        @NotNull(message = "Debe indicar el supplierId")
        Integer supplierId) {
}
