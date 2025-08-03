package com.lucascsilva.finance_control_api.application.mappers;

import com.lucascsilva.finance_control_api.application.dtos.auth.LoginResponseDto;
import com.lucascsilva.finance_control_api.domain.dtos.LoginResult;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {UserDtoMapper.class})
public interface LoginResponseMapper {
  LoginResponseDto toDto(LoginResult loginResult);
}
