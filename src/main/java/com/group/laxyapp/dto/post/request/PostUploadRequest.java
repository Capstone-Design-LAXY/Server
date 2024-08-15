package com.group.laxyapp.dto.post.request;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostUploadRequest {
    private Long postId;
    private Long id;
    private String title;
    private String contents;
    private String tag;
    private String photofile;
    private LocalDateTime updatedAt;


}
