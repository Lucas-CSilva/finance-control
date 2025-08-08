package com.lucascsilva.finance_control_api.domain.dtos;

import com.lucascsilva.finance_control_api.domain.models.User;
import lombok.Builder;

@Builder
public record LoginResult(String token, User user) {}
