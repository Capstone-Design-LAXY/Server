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


    public static void checkValidTitle(String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException(PostState.NULL_TITLE.getMessage());
        }
    }

    public static void checkValidContent(String content) {
        if (content.isBlank()) {
            throw new IllegalArgumentException(PostState.NULL_CONTENT.getMessage());
        }
    }


    public PostUploadRequest(Long id, String title, String contents, String tags, String photoFile) {
        checkValidTitle(title);
        checkValidContent(contents);

        this.id = id;
        this.title = title;
        this.contents = contents;
        this.tags = tags;
        this.photoFile = photoFile;
    }
}
