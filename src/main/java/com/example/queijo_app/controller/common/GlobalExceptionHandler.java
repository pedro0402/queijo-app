package com.example.queijo_app.controller.common;

import com.example.queijo_app.dto.ErrorField;
import com.example.queijo_app.dto.ErrorResponse;
import com.example.queijo_app.exception.CustomerNotFoundException;
import com.example.queijo_app.exception.DuplicateCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorField> errorFields = fieldErrors.stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation failed for one or more fields", errorFields);
    }

}
