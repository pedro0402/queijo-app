package com.example.queijo_app.repository;

import com.example.queijo_app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByNameAndAddress(String name, String address);

    boolean existsByNameAndAddressAndIdNot(String name, String address, Long id);
}
