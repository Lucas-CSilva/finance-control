package com.lucascsilva.finance_control_api.domain.models;

import java.time.Instant;
import lombok.Builder;

@Builder
public record User(
    String id, String name, String email, String password, Instant createdAt, Instant updatedAt) {}
