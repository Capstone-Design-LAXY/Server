package com.group.laxyapp.controller.post;


import com.group.laxyapp.domain.enums.PostState;
import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.dto.post.request.PostUploadRequest;
import com.group.laxyapp.service.post.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public List<Post> getPosts( ) {
        return postService.getPosts();
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<String> uploadPost(@RequestBody PostUploadRequest updateRequest) {
        try {
            postService.uploadPost(updateRequest);
            return ResponseEntity.ok(PostState.SUCCESS_UPLOAD.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/post")
    @ResponseBody
    public ResponseEntity<String> updatePost(@RequestParam(name = "post_id") Long postId,
        @RequestBody PostUploadRequest updateRequest) {
        try {
            postService.updatePost(postId, updateRequest);
            return ResponseEntity.ok(PostState.SUCCESS_UPDATE.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    // TODO: DELETE MAPPING 작성 중 오류 발생으로 PUT MAPPING REQUEST PARAM 수정
//    @DeleteMapping("/post/?{postId}")
//    @ResponseBody
//    public ResponseEntity<String> deletePost(@RequestParam("postId")String postId
//        , @RequestParam("id")String id) {
////        try {
////            postService.deletePost(postId, id);
////            return ResponseEntity.ok(PostState.SUCCESS_DELETE.getMessage());
////        } catch (IllegalArgumentException exception) {
////            return ResponseEntity.badRequest().body(exception.getMessage());
////        }
//        return ResponseEntity.ok(PostState.SUCCESS_DELETE.getMessage());
//    }
}
