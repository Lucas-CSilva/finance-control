package com.lucascsilva.finance_control_api.application.dtos;

import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;
import lombok.Builder;

@Builder
public record ErrorResponse(int status, String message, ErrorCodesEnum code) {}
