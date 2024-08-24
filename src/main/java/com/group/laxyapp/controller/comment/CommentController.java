package com.group.laxyapp.controller.comment;

import com.group.laxyapp.dto.comment.CommentResponse;
import com.group.laxyapp.service.comment.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{post_id}/comment")
    @ResponseBody
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable("post_id") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }
}
