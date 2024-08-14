package com.group.laxyapp.dto.post.request;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostUpdateRequest {
    private Long postId;
    private Long id;
    private String title;
    private String contents;
    private String tag;
    private String photofile;
    private LocalDateTime updatedAt;

    public void CheckValidUpload(String title, String contents) {
        Validator.checkValidUpload(title, contents);
    }
}
