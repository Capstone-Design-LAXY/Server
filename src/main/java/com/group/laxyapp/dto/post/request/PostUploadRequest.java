package com.group.laxyapp.dto.post.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class PostUploadRequest {
    private Long postId;
    private Long id;
    private String title;
    private String contents;
    private List<String> tag;
    private List<String> photofile;
    private LocalDateTime updatedAt;
    private Long likes;
    private Long viewed;
}
