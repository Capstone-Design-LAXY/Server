package com.group.laxyapp.controller.post;


import com.group.laxyapp.controller.validator.PostValidator;
import com.group.laxyapp.domain.enums.PostState;
import com.group.laxyapp.dto.post.PostUploadRequest;
import com.group.laxyapp.dto.post.PostResponse;
import com.group.laxyapp.service.post.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    @ResponseBody
    public ResponseEntity<List<PostResponse>> getPosts( ) {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/post/{post_id}")
    @ResponseBody
    public ResponseEntity<PostResponse> getPost(@PathVariable("post_id") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<?> uploadPost(@RequestBody PostUploadRequest updateRequest) {
        try {
            PostValidator.CheckValidUpload(updateRequest);
            return ResponseEntity.ok(postService.uploadPost(updateRequest));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/post/{post_id}")
    @ResponseBody
    public ResponseEntity<?> updatePost(@PathVariable("post_id") Long postId,
        @RequestBody PostUploadRequest updateRequest) {
        try {
            PostValidator.CheckValidUpload(updateRequest);
            return ResponseEntity.ok(postService.updatePost(postId, updateRequest));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/post/{post_id}")
    @ResponseBody
    public ResponseEntity<String> deletePost(@PathVariable("post_id")Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.ok(PostState.SUCCESS_DELETE.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
