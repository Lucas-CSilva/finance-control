package com.lucascsilva.finance_control_api.domain.services;

import com.lucascsilva.finance_control_api.domain.dtos.LoginResult;
import com.lucascsilva.finance_control_api.domain.exceptions.DomainException;
import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;
import com.lucascsilva.finance_control_api.domain.models.User;
import com.lucascsilva.finance_control_api.domain.ports.input.AuthPort;
import com.lucascsilva.finance_control_api.domain.ports.output.TokenPort;
import com.lucascsilva.finance_control_api.domain.ports.output.UserRepositoryPort;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService implements AuthPort {

  private final TokenPort tokenPort;
  private final PasswordEncoder passwordEncoder;
  private final UserRepositoryPort userRepository;
  private final ReactiveAuthenticationManager authenticationManager;

  @Override
  public Mono<LoginResult> login(String email, String password) {
    log.info("Attempting to login user with email: {}", email);
    return authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(email, password))
        .flatMap(
            authentication -> {
              UserDetails userDetails = (UserDetails) authentication.getPrincipal();
              String token = tokenPort.generateToken(userDetails);

              return userRepository
                  .findByEmail(userDetails.getUsername())
                  .map(userModel -> LoginResult.builder().token(token).user(userModel).build());
            });
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
                    .roles(List.of("ROLE_USER"))
                    .enabled(true)
                    .build()))
        .then();
  }
}
