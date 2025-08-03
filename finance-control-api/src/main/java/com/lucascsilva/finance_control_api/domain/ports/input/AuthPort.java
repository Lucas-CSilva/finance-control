package com.lucascsilva.finance_control_api.domain.ports.input;

import com.lucascsilva.finance_control_api.domain.dtos.LoginResult;
import reactor.core.publisher.Mono;

public interface AuthPort {
  Mono<LoginResult> login(String email, String password);

  Mono<Void> register(String email, String name, String password);
}
