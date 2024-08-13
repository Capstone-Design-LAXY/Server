package com.group.laxyapp.domain.enums;

public enum PostState {
    SUCCESS_UPLOAD("게시글 등록이 완료되었습니다."),
    NULL_CONTENT("게시글 내용을 작성해주세요."),
    NULL_TITLE("게시글 제목을 작성해주세요.");

    private final String message;

    PostState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
