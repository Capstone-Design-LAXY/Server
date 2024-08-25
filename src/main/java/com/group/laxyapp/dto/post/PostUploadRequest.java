package com.group.laxyapp.dto.post;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostUploadRequest {
    private final Long postId;
    private final Long userId;
    private final String title;
    private final String contents;
    private final List<String> tag;
    private final List<String> photoFile;
    private final List<String> createAt;
    private final LocalDateTime updatedAt;
    private final Long likes;
    private final Long viewed;
}
