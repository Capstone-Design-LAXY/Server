package com.group.laxyapp.dto.comment;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentRequest {
    private final Long commentId;
    private final Long userId;
    private final String contents;
}