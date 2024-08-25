package com.group.laxyapp.dto.comment;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentUploadRequest {
    private final Long userId;
    private final String contents;
}
