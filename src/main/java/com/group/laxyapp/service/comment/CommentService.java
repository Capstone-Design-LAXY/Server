package com.group.laxyapp.service.comment;

import com.group.laxyapp.domain.comment.CommentRepository;
import com.group.laxyapp.dto.comment.CommentResponse;
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
}
