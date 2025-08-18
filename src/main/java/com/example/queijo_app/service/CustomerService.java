package com.example.queijo_app.service;

import com.example.queijo_app.exception.CustomerNotFoundException;
import com.example.queijo_app.model.Customer;
import com.example.queijo_app.repository.CustomerRepository;
import com.example.queijo_app.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;

    public Customer save(Customer customer) {
        customerValidator.validate(customer);
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer must have an ID to be updated");
        }

        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customer.getId()));

        existingCustomer.setName(customer.getName());
        existingCustomer.setAddress(customer.getAddress());

        customerValidator.validate(existingCustomer);
        return customerRepository.save(existingCustomer);
    }

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id)));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id:" + id));
        customerRepository.delete(customer);
    }
}
