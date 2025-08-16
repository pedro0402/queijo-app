package com.example.queijo_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sale_id",nullable = false)//colocar nullable = false quer dizer que obrigatoriamente cada pagamento deve pertencer a uma venda
    private Sale sale;

    @NotBlank
    private String method;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @Column(name = "paid_at", nullable = false)
    private LocalDateTime paidAt;
}
