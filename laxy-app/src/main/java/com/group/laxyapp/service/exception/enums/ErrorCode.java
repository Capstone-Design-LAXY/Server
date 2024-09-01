package com.group.laxyapp.service.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final HttpStatus httpStatus;
}