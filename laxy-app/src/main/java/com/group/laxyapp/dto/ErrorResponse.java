package com.group.laxyapp.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ErrorResponse {
    private String code;
    private String exception;
    private String message;
}