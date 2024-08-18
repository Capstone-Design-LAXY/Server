package com.group.laxyapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    private int status;
    private String code;
    private String message;

}