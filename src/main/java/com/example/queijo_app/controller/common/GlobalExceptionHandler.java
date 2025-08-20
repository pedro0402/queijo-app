package com.example.queijo_app.controller.common;

import com.example.queijo_app.dto.ErrorResponse;
import com.example.queijo_app.exception.CustomerNotFoundException;
import com.example.queijo_app.exception.DuplicateCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException e) {
        return ErrorResponse.notFound(e.getMessage());
    }

    @ExceptionHandler(DuplicateCustomerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateCustomerException(DuplicateCustomerException e) {
        return ErrorResponse.conflict(e.getMessage());
    }

}
