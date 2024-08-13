package com.group.laxyapp.dto.post.request;

import com.group.laxyapp.domain.enums.PostState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostUploadRequest {

    private Long id;
    private String title;
    private String contents;
    private String tags;
    private String photoFile;


    public PostUploadRequest(Long id, String title, String contents, String tags, String photoFile) {
        if (title.isBlank()) {
            throw new IllegalArgumentException(PostState.NULL_TITLE.getMessage());
        }
        if (contents.isBlank()) {
            throw new IllegalArgumentException(PostState.NULL_CONTENT.getMessage());
        }
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.tags = tags;
        this.photoFile = photoFile;
    }

}
