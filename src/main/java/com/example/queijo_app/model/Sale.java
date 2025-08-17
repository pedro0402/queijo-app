package com.example.queijo_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer costumer;

    @NotNull
    @Column(name = "sale_date")
    private LocalDate saleDate;

    @NotNull
    @Column(name = "total_value")
    private BigDecimal totalValue;

    @NotNull
    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @NotNull
    private BigDecimal profit;
}
