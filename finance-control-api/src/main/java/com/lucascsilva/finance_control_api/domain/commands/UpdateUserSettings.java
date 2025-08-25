package com.lucascsilva.finance_control_api.domain.commands;

import io.micrometer.common.util.StringUtils;
import lombok.Builder;

@Builder
public record UpdateUserSettings(String name, String currentPassword, String newPassword) {

  public boolean isPasswordChangeRequested() {
    return StringUtils.isNotBlank(newPassword) && StringUtils.isNotBlank(currentPassword);
  }
}
