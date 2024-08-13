package com.group.laxyapp.dto.post.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostUploadRequest {

    private int id;
    private String title;
    private String contents;
    private String tags;
    private String photoFile;

    public PostUploadRequest(String title, String contents, String tags, String photoFile) {
        this.title = title;
        this.contents = contents;
        this.tags = tags;
        this.photoFile = photoFile;
    }
}
