package com.lucascsilva.finance_control_api.domain.exceptions;

import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;

public class InvalidCredentialsException extends DomainException {
  public InvalidCredentialsException() {
    super("Invalid email or password", ErrorCodesEnum.INVALID_CREDENTIALS);
  }
}
