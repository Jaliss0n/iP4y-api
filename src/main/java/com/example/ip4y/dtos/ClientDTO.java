package com.example.ip4y.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO (@NotBlank String cpf, @NotBlank String name, @NotBlank String surname, @NotBlank String dateOfBirthday, @NotBlank String email, @NotBlank String gender) {

}
