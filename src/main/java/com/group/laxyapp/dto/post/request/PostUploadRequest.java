package com.group.laxyapp.dto.post.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostUploadRequest {
    private Long postId;
    private Long userId;
    private String title;
    private String contents;
    private List<String> tag;
    private List<String> photoFile;
    private List<String> createAt;
    private LocalDateTime updatedAt;
    private Long likes;
    private Long viewed;
}