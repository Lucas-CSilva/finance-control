package com.lucascsilva.finance_control_api.infrastructure.security;

import com.lucascsilva.finance_control_api.domain.exceptions.InvalidCredentialsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {

  private final UserDetailsServiceImpl userDetailsService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    log.info("Authenticating user: {}", authentication.getName());
    return Mono.just(authentication)
        .flatMap(this::verifyAuthentication)
        .flatMap(this::getAuthentication);
  }

  private Mono<UserDetails> verifyAuthentication(Authentication authentication) {

    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    return userDetailsService
        .findByUsername(username)
        .switchIfEmpty(Mono.error(new InvalidCredentialsException()))
        .flatMap(
            userDetails -> {
              log.info("Verifying authentication for user: {}", username);
              return passwordEncoder.matches(password, userDetails.getPassword())
                  ? Mono.just(userDetails)
                  : Mono.error(new InvalidCredentialsException());
            });
  }

  private Mono<Authentication> getAuthentication(UserDetails userDetails) {
    return Mono.just(
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
  }
}
