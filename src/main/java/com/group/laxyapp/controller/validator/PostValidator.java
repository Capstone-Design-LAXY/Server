package com.group.laxyapp.controller.validator;

import com.group.laxyapp.domain.enums.PostState;
import com.group.laxyapp.dto.post.PostUploadRequest;

public class PostValidator {

    public static void CheckValidUpload(PostUploadRequest uploadRequest) {
        checkValidTitle(uploadRequest.getTitle());
        checkValidContent(uploadRequest.getContents());
    }

    private static void checkValidTitle(String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException(PostState.NULL_TITLE.getMessage());
        }
    }

    private static void checkValidContent(String contents) {
        if (contents.isBlank()) {
            throw new IllegalArgumentException(PostState.NULL_CONTENT.getMessage());
        }
    }
}
