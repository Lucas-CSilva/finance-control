package com.lucascsilva.finance_control_api.domain.ports.output;

import com.lucascsilva.finance_control_api.domain.models.User;
import reactor.core.publisher.Mono;

public interface UserRepositoryPort {
  Mono<User> findByEmail(String email);

  Mono<User> save(User user);

  Mono<User> findById(String id);
}
