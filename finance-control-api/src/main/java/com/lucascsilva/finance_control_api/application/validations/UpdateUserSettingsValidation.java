package com.lucascsilva.finance_control_api.application.validations;

import com.lucascsilva.finance_control_api.domain.commands.UpdateUserSettings;
import com.lucascsilva.finance_control_api.domain.exceptions.DomainException;
import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateUserSettingsValidation {

  public Mono<Void> validate(UpdateUserSettings command) {
    if (StringUtils.isNotBlank(command.newPassword())
        && StringUtils.isBlank(command.currentPassword())) {

      return Mono.error(
          new DomainException(
              "Current password must be provided to change the password",
              ErrorCodesEnum.VALIDATION_ERROR));
    }

    return Mono.empty();
  }
}
