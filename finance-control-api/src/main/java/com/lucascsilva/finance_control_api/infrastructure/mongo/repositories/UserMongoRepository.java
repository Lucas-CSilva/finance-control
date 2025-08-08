package com.lucascsilva.finance_control_api.infrastructure.mongo.repositories;

import com.lucascsilva.finance_control_api.infrastructure.mongo.documents.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserMongoRepository extends ReactiveMongoRepository<UserDocument, String> {

  Mono<UserDocument> findByEmail(String email);
}
