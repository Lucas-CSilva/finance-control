package com.lucascsilva.finance_control_api.application.mappers;

import com.lucascsilva.finance_control_api.application.dtos.auth.UserDto;
import com.lucascsilva.finance_control_api.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
  UserDto toDto(User user);
}
