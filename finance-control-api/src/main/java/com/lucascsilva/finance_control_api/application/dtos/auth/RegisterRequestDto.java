package com.lucascsilva.finance_control_api.application.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(
    @Email String email, @NotBlank String name, @NotBlank String password) {}
