package com.lucascsilva.finance_control_api.infrastructure.mongo.mappers;

import com.lucascsilva.finance_control_api.domain.models.User;
import com.lucascsilva.finance_control_api.infrastructure.mongo.documents.UserDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toModel(UserDocument userDocument);

  UserDocument toDocument(User user);
}
