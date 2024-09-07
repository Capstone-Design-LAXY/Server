package com.group.laxyapp.service.exception;

import com.group.laxyapp.service.exception.enums.ErrorCode;
import com.group.laxyapp.service.exception.enums.ErrorMessage;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final ErrorMessage errorMessage;

    public CustomException(ErrorCode errorCode, ErrorMessage message) {
        this.errorCode = errorCode;
        this.errorMessage = message;
    }
}
