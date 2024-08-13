package com.group.laxyapp.domain.enums;

public enum PostState {
    state_201("게시글 등록이 완료되었습니다.");

    private final String message;

    PostState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
