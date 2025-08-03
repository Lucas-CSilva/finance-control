package com.lucascsilva.finance_control_api.application;

import com.lucascsilva.finance_control_api.application.dtos.ErrorResponse;
import com.lucascsilva.finance_control_api.domain.exceptions.DomainException;
import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;
import com.lucascsilva.finance_control_api.infrastructure.exceptionhandling.ExceptionHttpStatusMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

  private final ExceptionHttpStatusMapper statusMapper;

  @ExceptionHandler(DomainException.class)
  public Mono<ResponseEntity<ErrorResponse>> handleDomainException(DomainException ex) {
    var status = statusMapper.getHttpStatus(ex.getErrorCode());
    return Mono.just(
        ResponseEntity.status(status)
            .body(
                ErrorResponse.builder()
                    .status(status.value())
                    .message(ex.getMessage())
                    .code(ex.getErrorCode())
                    .build()));
  }

  @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
  public Mono<ResponseEntity<ErrorResponse>> handleUnsupportedMediaType(
      UnsupportedMediaTypeStatusException ex) {
    log.warn("Unsupported Media Type: {}", ex.getMessage());
    return Mono.just(
        ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(
                ErrorResponse.builder()
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                    .message(ex.getMessage())
                    .code(ErrorCodesEnum.UNSUPPORTED_MEDIA_TYPE)
                    .build()));
  }

  @ExceptionHandler(Exception.class)
  public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
    log.error("Internal server error: {}, stack: {}", ex.getMessage(), ex.getStackTrace());
    return Mono.just(
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("An unexpected error occurred")
                    .code(ErrorCodesEnum.INTERNAL_SERVER_ERROR)
                    .build()));
  }
}
