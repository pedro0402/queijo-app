package com.example.queijo_app.mapper;

import com.example.queijo_app.dto.CustomerDTO;
import com.example.queijo_app.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDTO customerDTO);


    List<CustomerDTO> customerToCustomersDTOs(List<Customer> customerStream);


    CustomerDTO toCustomerDTO(Customer customer);
}
