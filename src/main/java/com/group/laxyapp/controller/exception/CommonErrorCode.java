package com.group.laxyapp.controller.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST),

    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED),

    POST_UPDATE_ERROR(HttpStatus.FORBIDDEN),
    POST_DELETE_ERROR(HttpStatus.FORBIDDEN),

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND),
    DISPLAY_NOT_FOUND(HttpStatus.NOT_FOUND),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final HttpStatus httpStatus;
}
