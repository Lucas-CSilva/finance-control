package com.lucascsilva.finance_control_api.application.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequestDto(@Email String email, @NotBlank String password) {}
