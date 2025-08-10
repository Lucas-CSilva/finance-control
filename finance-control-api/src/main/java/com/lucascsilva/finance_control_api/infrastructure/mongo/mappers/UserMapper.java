package com.lucascsilva.finance_control_api.infrastructure.mongo.mappers;

import com.lucascsilva.finance_control_api.domain.models.User;
import com.lucascsilva.finance_control_api.infrastructure.mongo.documents.UserDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toModel(UserDocument userDocument);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  UserDocument toDocument(User user);
}
