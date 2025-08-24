package com.lucascsilva.finance_control_api.infrastructure.exceptionhandling;

import com.lucascsilva.finance_control_api.domain.exceptions.enums.ErrorCodesEnum;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHttpStatusMapper {
  private static final Map<ErrorCodesEnum, HttpStatus> errorCodeToHttpStatusMap =
      Map.of(
          ErrorCodesEnum.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED,
          ErrorCodesEnum.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
          ErrorCodesEnum.UNSUPPORTED_MEDIA_TYPE, HttpStatus.UNSUPPORTED_MEDIA_TYPE,
          ErrorCodesEnum.ACCESS_DENIED, HttpStatus.FORBIDDEN,
          ErrorCodesEnum.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);

  public HttpStatus getHttpStatus(ErrorCodesEnum errorCode) {
    return errorCodeToHttpStatusMap.getOrDefault(errorCode, HttpStatus.BAD_REQUEST);
  }
}
