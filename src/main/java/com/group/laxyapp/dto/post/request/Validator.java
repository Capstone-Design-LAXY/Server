package com.group.laxyapp.dto.post.request;

import com.group.laxyapp.domain.enums.PostState;

public class Validator {

    public static void checkValidUpload(String title, String contents) {
        checkValidTitle(title);
        checkValidContent(contents);
    }

    public static void checkValidTitle(String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException(PostState.NULL_TITLE.getMessage());
        }
    }

    public static void checkValidContent(String contents) {
        if (contents.isBlank()) {
            throw new IllegalArgumentException(PostState.NULL_CONTENT.getMessage());
        }
    }
}
