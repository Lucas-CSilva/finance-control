package com.lucascsilva.finance_control_api.domain.models;

import java.util.List;
import lombok.Builder;

@Builder
public record User(
    String id, String name, String email, String password, List<String> roles, Boolean enabled) {}
