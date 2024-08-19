package com.group.laxyapp.controller.post;

import com.group.laxyapp.dto.post.request.PostUploadRequest;
import com.group.laxyapp.dto.post.response.PostResponse;
import com.group.laxyapp.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    @ResponseBody
    public ResponseEntity<List<PostResponse>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/post/{post_id}")
    @ResponseBody
    public ResponseEntity<PostResponse> getPost(@PathVariable("post_id") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<PostResponse> uploadPost(@RequestHeader("Authorization") String token,
                                                   @RequestBody PostUploadRequest request) {
        return ResponseEntity.ok(postService.uploadPost(token.substring(7), request));
    }

    @PutMapping("/post/{post_id}")
    @ResponseBody
    public ResponseEntity<?> updatePost(@RequestHeader("Authorization") String token,
                                        @PathVariable("post_id") Long postId,
                                        @RequestBody PostUploadRequest request) {
        return ResponseEntity.ok(postService.updatePost(token.substring(7), postId, request));
    }

    @DeleteMapping("/post/{post_id}")
    @ResponseBody
    public void deletePost(@RequestHeader("Authorization") String token,
                           @PathVariable("post_id") Long postId) {
        postService.deletePost(token.substring(7), postId);
    }
}
