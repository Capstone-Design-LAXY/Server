package com.group.laxyapp.controller.Exception;

import com.group.laxyapp.dto.ErrorResponse;
import javax.naming.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(IllegalArgumentException exception) {
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER
            , ErrorMessage.UNAUTHORIZED.getMessage());
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(makeErrorResponse(errorCode, message));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
            .code(errorCode.name())
            .message(errorCode.getHttpStatus().toString())
            .message(message)
            .build();
    }

}
