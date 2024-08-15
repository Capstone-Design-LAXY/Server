package com.group.laxyapp.dto.post.response;


import com.group.laxyapp.domain.post.Post;
import java.time.LocalDateTime;

public class PostResponse {
    public final Long postId;
    public final Long id;
    public final String title;
    public final String contents;
    public final String tag;
    public final String photofile;
    public final LocalDateTime updatedAt;

    public PostResponse(Post post) {
        this.postId = post.getPostId();
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.tag = post.getTag();
        this.photofile = post.getPhotofile();
        this.updatedAt = post.getUpdatedAt();
    }
}
