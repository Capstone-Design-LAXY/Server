package com.group.laxyapp.dto.comment;

import com.group.laxyapp.domain.comment.Comment;
import java.time.LocalDateTime;

public class CommentResponse {
    public final Long commentId;
    public final Long postId;
    public final Long userId;
    public final String contents;
    public final LocalDateTime createdAt;
    public final LocalDateTime updatedAt;
    public final Long likes;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.postId = comment.getPostId();
        this.userId = comment.getUserId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.likes = comment.getLikes();
    }
}
