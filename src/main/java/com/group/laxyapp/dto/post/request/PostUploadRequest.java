package com.group.laxyapp.dto.post.request;

import lombok.Getter;
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
        Validator.checkValidUpload(title, contents);

        this.id = id;
        this.title = title;
        this.contents = contents;
        this.tags = tags;
        this.photoFile = photoFile;
    }
}
