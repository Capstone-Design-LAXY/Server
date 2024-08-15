package com.group.laxyapp.controller.post;


import com.group.laxyapp.controller.validator.PostValidator;
import com.group.laxyapp.domain.enums.PostState;
import com.group.laxyapp.dto.post.request.PostUploadRequest;
import com.group.laxyapp.dto.post.response.PostResponse;
import com.group.laxyapp.service.post.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    @ResponseBody
    public List<PostResponse> getPosts( ) {
        return postService.getPosts();
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<String> uploadPost(@RequestBody PostUploadRequest updateRequest) {
        try {
            PostValidator.CheckValidUpload(updateRequest);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        postService.uploadPost(updateRequest);
        return ResponseEntity.ok(PostState.SUCCESS_UPLOAD.getMessage());
    }

    @PutMapping("/post")
    @ResponseBody
    public ResponseEntity<String> updatePost(@RequestParam(name = "post_id") Long postId,
        @RequestBody PostUploadRequest updateRequest) {
        try {
            PostValidator.CheckValidUpload(updateRequest);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        postService.updatePost(postId, updateRequest);
        return ResponseEntity.ok(PostState.SUCCESS_UPDATE.getMessage());
    }

    @DeleteMapping("/post")
    @ResponseBody
    public ResponseEntity<String> deletePost(@RequestParam("post_id")Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.ok(PostState.SUCCESS_DELETE.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
