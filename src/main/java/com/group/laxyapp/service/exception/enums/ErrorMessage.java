package com.group.laxyapp.service.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
    UNAUTHORIZED_LOGIN("로그인 후에 이용하실 수 있습니다."),
    UNAUTHORIZED_TOKEN("토큰 검증 중 오류가 발생했습니다."),
    FORBIDDEN_DENIED("접근 권한이 없습니다."),
    FORBIDDEN_DELETE_POST("게시글 삭제 권한이 없습니다."),
    NOT_FOUND_POST("해당 게시글을 찾을 수 없습니다"),
    ;

    private final String message;
}
