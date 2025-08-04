package com.lucascsilva.finance_control_api.domain.exceptions;

import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DomainException extends RuntimeException {
  private final ErrorCodesEnum errorCode;

  public DomainException(String message, ErrorCodesEnum errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
