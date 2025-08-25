package com.lucascsilva.finance_control_api.domain.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String id;
  private String name;
  private String email;
  private String password;
  private List<String> roles;
  private Boolean enabled;

  public Mono<User> updateName(String name) {
    this.name = name;
    return Mono.just(this);
  }

  public Mono<User> updatePassword(String password) {
    this.password = password;
    return Mono.just(this);
  }
}
