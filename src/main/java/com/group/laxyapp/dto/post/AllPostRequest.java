package com.group.laxyapp.dto.post;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AllPostRequest {
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private int commentCount;
    private Long likes;
    private List<String> photoFile;
    private Long postId;
}
