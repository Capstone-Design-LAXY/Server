package com.group.laxyapp.service.post;

import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.dto.post.PostUploadRequest;
import com.group.laxyapp.dto.post.PostResponse;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponse uploadPost(PostUploadRequest request) {
        Post post = Post.builder()
            .userId(request.getUserId())
            .title(request.getTitle())
            .contents(request.getContents())
            .tag(request.getTag())
            .photoFile(request.getPhotoFile()).build();
        return new PostResponse(postRepository.save(post));
    }

    @Transactional
    public PostResponse updatePost(Long postId, PostUploadRequest request) {
        return new PostResponse(postRepository.save(setUpdatePost(postId, request)));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        return postRepository.findAll(Sort.by(Direction.DESC, "createdAt"))
            .stream().map(PostResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {
        return new PostResponse(findPostById(postId).increaseViewed());
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.delete(findPostById(postId));
    }

    private Post setUpdatePost(Long postId, PostUploadRequest request) {
        return findPostById(postId).toBuilder()
            .title(request.getTitle())
            .contents(request.getContents())
            .tag(request.getTag())
            .photoFile(request.getPhotoFile())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    private Post findPostById(Long postId) {
        return postRepository.findByPostId(postId)
            .orElseThrow(IllegalArgumentException::new);
    }
}
