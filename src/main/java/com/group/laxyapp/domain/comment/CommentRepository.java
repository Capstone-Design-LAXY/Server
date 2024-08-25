package com.group.laxyapp.domain.comment;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
    Optional<Comment> findByCommentId(Long commentId);
}
