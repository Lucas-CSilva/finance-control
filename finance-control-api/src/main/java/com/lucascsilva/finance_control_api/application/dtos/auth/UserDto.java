package com.lucascsilva.finance_control_api.application.dtos.auth;

import lombok.Builder;

@Builder
public record UserDto(String id, String name, String email) {}
