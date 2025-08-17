package com.example.queijo_app.validator;

import com.example.queijo_app.exception.DuplicateCustomerException;
import com.example.queijo_app.model.Customer;
import com.example.queijo_app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidator {

    private final CustomerRepository customerRepository;

    public void validate(Customer customer) {
        if (isCustomerRegistered(customer)) {
            throw new DuplicateCustomerException("A customer with the same name and address already exists.");
        }
    }

    private boolean isCustomerRegistered(Customer customer) {
        if (customer.getId() == null) {
            return customerRepository.existsByNameAndAddress(customer.getName(), customer.getAddress());
        } else {
            return customerRepository.existsByNameAndAddressAndIdNot(
                    customer.getName(),
                    customer.getAddress(),
                    customer.getId());
        }
    }
}
