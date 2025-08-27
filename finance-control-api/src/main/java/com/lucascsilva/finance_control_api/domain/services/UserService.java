package com.lucascsilva.finance_control_api.domain.services;

import com.lucascsilva.finance_control_api.domain.commands.UpdateUserSettings;
import com.lucascsilva.finance_control_api.domain.exceptions.DomainException;
import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;
import com.lucascsilva.finance_control_api.domain.models.User;
import com.lucascsilva.finance_control_api.domain.ports.input.UserPort;
import com.lucascsilva.finance_control_api.domain.ports.output.UserRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserPort {

  private final UserRepositoryPort userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<User> updateSettings(String id, UpdateUserSettings command) {
    return userRepository
        .findById(id)
        .switchIfEmpty(
            Mono.error(new DomainException("User not found", ErrorCodesEnum.RESOURCE_NOT_FOUND)))
        .flatMap(user -> user.updateName(command.name()))
        .flatMap(user -> updatePassword(user, command))
        .flatMap(userRepository::save);
  }

  private Mono<User> updatePassword(User user, UpdateUserSettings command) {
    if (!command.isPasswordChangeRequested()) {
      return Mono.just(user);
    }

    return isCurrentPasswordValid(user, command.currentPassword())
        .then(user.updatePassword(passwordEncoder.encode(command.newPassword())));
  }

  private Mono<Void> isCurrentPasswordValid(User user, String currentPassword) {

    if (passwordEncoder.matches(currentPassword, user.getPassword())) {
      return Mono.empty();
    }

    return Mono.error(
        new DomainException("Current password is incorrect", ErrorCodesEnum.ACCESS_DENIED));
  }
}
