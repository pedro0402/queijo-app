package com.example.queijo_app.controller;

import com.example.queijo_app.dto.CustomerDTO;
import com.example.queijo_app.helper.NullAwareBeanUtilsBean;
import com.example.queijo_app.mapper.CustomerMapper;
import com.example.queijo_app.model.Customer;
import com.example.queijo_app.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController implements GenericController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customerService.save(customer);
        URI uri = headerLocation(customer.getId());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchUpdate(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            Customer existingCustomer = customerService.findById(id);
            nullAwareBeanUtilsBean.copyProperties(existingCustomer, customerMapper.toEntity(customerDTO));
            customerService.update(existingCustomer);
            return ResponseEntity.ok(customerMapper.toCustomerDTO(existingCustomer));
        } catch (InvocationTargetException | IllegalAccessException exception) {
            throw new RuntimeException("error updating customer ", exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = customerService.findById(id);

        customer.setName(customerDTO.name());
        customer.setAddress(customerDTO.address());

        customerService.update(customer);

        return ResponseEntity.ok(customerMapper.toCustomerDTO(customer));
    }
}
