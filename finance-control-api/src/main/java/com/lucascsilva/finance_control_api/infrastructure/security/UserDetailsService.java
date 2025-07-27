package com.lucascsilva.finance_control_api.infrastructure.security;

import com.lucascsilva.finance_control_api.domain.ports.output.UserRepositoryPort;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService {

  private final UserRepositoryPort userRepository;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return userRepository
        .findByEmail(username)
        .map(user -> new User(user.email(), user.password(), Collections.emptyList()));
  }
}
