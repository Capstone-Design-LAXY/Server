package com.group.laxyapp.service.comment;

import com.group.laxyapp.domain.comment.Comment;
import com.group.laxyapp.domain.comment.CommentRepository;
import com.group.laxyapp.dto.comment.CommentResponse;
import com.group.laxyapp.dto.comment.CommentRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPostId(Long postId) {
        return commentRepository.findAll(Sort.by(Direction.ASC, "createdAt"))
            .stream().filter(comment -> comment.getPostId().equals(postId))
            .map(CommentResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse uploadComment(CommentRequest request, Long postId) {
        Comment comment = Comment.builder()
            .postId(postId)
            .userId(request.getUserId())
            .contents(request.getContents()).build();
        return new CommentResponse(commentRepository.save(comment));
    }

    public void deleteComment(CommentRequest request) {
        commentRepository.delete(findCommentById(request.getCommentId()));
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findByCommentId(commentId)
            .orElseThrow(IllegalArgumentException::new);
    }

}
