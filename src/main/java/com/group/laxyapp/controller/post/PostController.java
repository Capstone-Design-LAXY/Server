package com.group.laxyapp.controller.post;


import com.group.laxyapp.domain.enums.PostState;
import com.group.laxyapp.dto.post.request.PostUploadRequest;
import com.group.laxyapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<String> uploadPost(@RequestBody PostUploadRequest uploadRequest) {
        try {
            postService.uploadPost(uploadRequest);
            return ResponseEntity.ok(PostState.SUCCESS_UPLOAD.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


}
