package com.group.laxyapp.controller.Exception;

import com.group.laxyapp.dto.ErrorResponse;
import javax.security.sasl.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException exception) {
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER
            , exception.getMessage()
            , ErrorMessage.UNAUTHORIZED.getMessage());
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String exception, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(makeErrorResponse(errorCode, exception, message));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String exception, String message) {
        return ErrorResponse.builder()
            .code(errorCode.name())
            .exception(exception)
            .message(message)
            .build();
    }

}
