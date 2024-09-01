package com.group.laxyapp.service.exception;

import com.group.laxyapp.service.exception.enums.ErrorCode;
import com.group.laxyapp.service.exception.enums.ErrorMessage;
import com.group.laxyapp.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException exception) {
        return handleExceptionInternal(exception.getErrorCode(), exception.getErrorMessage());
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, ErrorMessage errorMessage) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, errorMessage));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, ErrorMessage message) {
        return ErrorResponse.builder()
                .error(errorCode.getHttpStatus().toString())
                .message(message.getMessage())
                .build();
    }

}