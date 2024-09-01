package com.group.laxyapp.controller.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {

    UNAUTHORIZED("로그인 후에 이용 가능합니다."),

    POST_UPDATE_ERROR("이 게시물을 업데이트할 권한이 없습니다."),
    POST_DELETE_ERROR("이 게시물을 삭제할 권한이 없습니다."),

    DISPLAY_NOT_FOUND("게시물을 찾을 수 없습니다.")
    ;


    private final String message;
}