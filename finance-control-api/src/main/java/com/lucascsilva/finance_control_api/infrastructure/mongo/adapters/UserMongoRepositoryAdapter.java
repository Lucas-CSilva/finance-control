package com.lucascsilva.finance_control_api.infrastructure.mongo.adapters;

import com.lucascsilva.finance_control_api.domain.models.User;
import com.lucascsilva.finance_control_api.domain.ports.output.UserRepositoryPort;
import com.lucascsilva.finance_control_api.infrastructure.mongo.mappers.UserMapper;
import com.lucascsilva.finance_control_api.infrastructure.mongo.repositories.UserMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserMongoRepositoryAdapter implements UserRepositoryPort {

  private final UserMongoRepository userMongoRepository;
  private final UserMapper userMapper;

  @Override
  public Mono<User> findByEmail(String email) {
    return userMongoRepository.findByEmail(email).map(userMapper::toModel);
  }

  @Override
  public Mono<User> findById(String id) {
    return userMongoRepository.findById(id).map(userMapper::toModel);
  }

  @Override
  public Mono<User> save(User user) {
    return userMongoRepository.save(userMapper.toDocument(user)).map(userMapper::toModel);
  }
}
