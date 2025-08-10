package com.lucascsilva.finance_control_api.application.rest;

import com.lucascsilva.finance_control_api.application.dtos.auth.LoginRequestDto;
import com.lucascsilva.finance_control_api.application.dtos.auth.LoginResponseDto;
import com.lucascsilva.finance_control_api.application.dtos.auth.RegisterRequestDto;
import com.lucascsilva.finance_control_api.application.mappers.LoginResponseMapper;
import com.lucascsilva.finance_control_api.domain.ports.input.AuthPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

  private final AuthPort authPort;
  private final LoginResponseMapper loginResponseMapper;

  @PostMapping(value = "/login")
  public Mono<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequest) {
    return authPort
        .login(loginRequest.email(), loginRequest.password())
        .map(loginResponseMapper::toDto);
  }

  @PostMapping(value = "/register")
  public Mono<ResponseEntity<Void>> register(
      @RequestBody @Valid RegisterRequestDto registerRequest) {
    log.info("Registering user with email: {}", registerRequest.email());
    return authPort
        .register(registerRequest.email(), registerRequest.name(), registerRequest.password())
        .thenReturn(ResponseEntity.noContent().build());
  }
}
