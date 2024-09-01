package com.group.laxyapp.controller.exception;

public class CommonException extends RuntimeException {
    private final CommonErrorCode errorCode;
    private final String errorMessage;

    public CommonException(CommonErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public CommonErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}