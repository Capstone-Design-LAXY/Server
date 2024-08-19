package com.group.laxyapp.dto.post.response;


import com.group.laxyapp.domain.post.Post;
import java.time.LocalDateTime;
import java.util.List;


public class PostResponse {
    public final Long postId;
    public final Long userId;
    public final String title;
    public final String contents;
    public final List<String> tag;
    public final List<String> photoFile;

    public final LocalDateTime createdAt;
    public final LocalDateTime updatedAt;
    public final Long likes;
    public final Long viewed;

    public PostResponse(Post post) {
        this.postId = post.getPostId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.tag = post.getTag();
        this.photoFile = post.getPhotoFile();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.likes = post.getLikes();
        this.viewed = post.getViewed();
    }
}