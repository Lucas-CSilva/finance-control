package com.lucascsilva.finance_control_api.domain.services;

import com.lucascsilva.finance_control_api.domain.dtos.LoginResult;
import com.lucascsilva.finance_control_api.domain.exceptions.DomainException;
import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;
import com.lucascsilva.finance_control_api.domain.models.User;
import com.lucascsilva.finance_control_api.domain.ports.input.AuthPort;
import com.lucascsilva.finance_control_api.domain.ports.output.UserRepositoryPort;
import com.lucascsilva.finance_control_api.infrastructure.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthService implements AuthPort {

  private final TokenService tokenService;
  private final PasswordEncoder passwordEncoder;
  private final UserRepositoryPort userRepository;

  @Override
  public Mono<LoginResult> login(String email, String password) {
    return userRepository
        .findByEmail(email)
        .filter(user -> passwordEncoder.matches(password, user.password()))
        .map(
            user ->
                LoginResult.builder().token(tokenService.generateToken(user)).user(user).build())
        .switchIfEmpty(
            Mono.error(
                new DomainException(
                    "Invalid email or password", ErrorCodesEnum.INVALID_CREDENTIALS)));
  }

  @Override
  public Mono<Void> register(String email, String name, String password) {
    return userRepository
        .findByEmail(email)
        .flatMap(
            user ->
                Mono.error(
                    new DomainException("Email already registered", ErrorCodesEnum.GENERIC_ERROR)))
        .switchIfEmpty(
            userRepository.save(
                User.builder()
                    .email(email)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .build()))
        .then();
  }
}
