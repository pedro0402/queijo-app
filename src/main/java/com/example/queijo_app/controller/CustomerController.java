package com.example.queijo_app.controller;

import com.example.queijo_app.dto.CustomerDTO;
import com.example.queijo_app.mapper.CustomerMapper;
import com.example.queijo_app.model.Customer;
import com.example.queijo_app.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController implements GenericController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customerService.save(customer);
        URI uri = headerLocation(customer.getId());
        return ResponseEntity.created(uri).build();
    }
}
