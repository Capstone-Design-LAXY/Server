package com.group.laxyapp.dto.post.response;


import com.group.laxyapp.domain.post.Post;
import java.time.LocalDateTime;
import java.util.List;

public class PostResponse {
    public final Long postId;
    public final Long id;
    public final String title;
    public final String contents;
    public final List<String> tag;
    public final List<String> photofile;
    public final LocalDateTime updatedAt;
    public final Long likes;
    public final Long viewed;

    public PostResponse(Post post) {
        this.postId = post.getPostId();
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.tag = post.getTag();
        this.photofile = post.getPhotofile();
        this.updatedAt = post.getUpdatedAt();
        this.likes = post.getLikes();
        this.viewed = post.getLikes();
    }
}
