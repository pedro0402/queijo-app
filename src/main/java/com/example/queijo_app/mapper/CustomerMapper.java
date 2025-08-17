package com.example.queijo_app.mapper;

import com.example.queijo_app.dto.CustomerDTO;
import com.example.queijo_app.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDTO customerDTO);
    CustomerDTO toCustomerDTO(Customer customer);
}
