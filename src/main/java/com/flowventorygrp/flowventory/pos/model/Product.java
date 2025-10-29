package com.flowventorygrp.flowventory.pos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le type est obligatoire")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @NotBlank(message = "La marque est obligatoire")
    private String brand;

    @NotBlank(message = "Le modèle est obligatoire")
    private String model;

    @Min(value = 1990, message = "Année invalide")
    @Max(value = 2030, message = "Année trop élevée")
    private Integer year;

    @DecimalMin(value = "0.01", message = "Le prix doit être positif")
    private BigDecimal price;
}

