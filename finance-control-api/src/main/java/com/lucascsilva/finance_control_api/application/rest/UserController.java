package com.lucascsilva.finance_control_api.application.rest;

import com.lucascsilva.finance_control_api.application.dtos.auth.UserDto;
import com.lucascsilva.finance_control_api.application.mappers.UserDtoMapper;
import com.lucascsilva.finance_control_api.application.validations.UpdateUserSettingsValidation;
import com.lucascsilva.finance_control_api.domain.commands.UpdateUserSettings;
import com.lucascsilva.finance_control_api.domain.ports.input.UserPort;
import com.lucascsilva.finance_control_api.infrastructure.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserPort userPort;
  private final UserDtoMapper userMapper;
  private final UpdateUserSettingsValidation updateUserSettingsValidation;

  @PatchMapping(value = "/me/update-settings")
  public Mono<UserDto> updateSettings(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateUserSettings command) {

    return updateUserSettingsValidation
        .validate(command)
        .then(userPort.updateSettings(userDetails.getId(), command))
        .map(userMapper::toDto);
  }
}
