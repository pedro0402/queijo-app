package com.example.queijo_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerDTO(

        Long id,

        @NotBlank(message = "Must be a value")
        @Size(min = 2, max = 100, message = "Field out of pattern")
        String name,

        @NotBlank(message = "Must be a value")
        @Size(min = 2, max = 200, message = "Field out of pattern")
        String address
) {
}
