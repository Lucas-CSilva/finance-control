package com.lucascsilva.finance_control_api.infrastructure.security;

import com.lucascsilva.finance_control_api.infrastructure.mongo.mappers.UserMapper;
import com.lucascsilva.finance_control_api.infrastructure.mongo.repositories.UserMongoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

  private final UserMapper userMapper;
  private final UserMongoRepository userRepository;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    log.info("Finding user by username: {}", username);
    return userRepository
        .findByEmail(username)
        .map(userMapper::toUserDetails)
        .cast(UserDetails.class);
  }
}
