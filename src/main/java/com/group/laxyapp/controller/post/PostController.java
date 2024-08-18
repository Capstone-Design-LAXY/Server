package com.group.laxyapp.controller.post;


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
    public ResponseEntity<PostResponse> uploadPost(@RequestBody PostUploadRequest updateRequest) {
        return ResponseEntity.ok(postService.uploadPost(updateRequest));
    }

    @PutMapping("/post/{post_id}")
    @ResponseBody
    public ResponseEntity<?> updatePost(@PathVariable("post_id") Long postId,
        @RequestBody PostUploadRequest updateRequest) {
            return ResponseEntity.ok(postService.updatePost(postId, updateRequest));
    }

    @DeleteMapping("/post/{post_id}")
    @ResponseBody
    public void deletePost(@PathVariable("post_id")Long postId) {
            postService.deletePost(postId);
    }
}
