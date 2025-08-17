package com.example.queijo_app.service;

import com.example.queijo_app.model.Customer;
import com.example.queijo_app.repository.CustomerRepository;
import com.example.queijo_app.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;

    public Customer save(Customer customer) {
        customerValidator.validate(customer);
        return customerRepository.save(customer);
    }

}
