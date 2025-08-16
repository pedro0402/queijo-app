package com.example.queijo_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Column(name = "unit", nullable = false)
    @Enumerated(EnumType.STRING)
    private WeightUnits weightUnits;

    @NotNull
    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice;

    @NotNull
    @Column(name = "sale_price", nullable = false)
    private BigDecimal salePrice;
}
