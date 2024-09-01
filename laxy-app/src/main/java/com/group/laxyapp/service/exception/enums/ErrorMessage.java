package com.group.laxyapp.service.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
    UNAUTHORIZED_DELETE_POST("삭제 권한이 없습니다."),
    NOT_FOUND_POST("해당 게시글을 찾을 수 없습니다"),
    ;

    private final String message;
}