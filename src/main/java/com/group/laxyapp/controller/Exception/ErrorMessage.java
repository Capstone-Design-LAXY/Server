package com.group.laxyapp.controller.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
    UNAUTHORIZED("로그인 후에 이용 가능합니다.");

    private final String message;
}
