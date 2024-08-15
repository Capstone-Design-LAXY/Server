package com.group.laxyapp.service.post;

import com.group.laxyapp.domain.enums.PostState;
import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.dto.post.request.PostUploadRequest;
import com.group.laxyapp.dto.post.response.PostResponse;
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
    public void uploadPost(PostUploadRequest request) {
        Post post = new Post(
            request.getId(),
            request.getTitle(),
            request.getContents(),
            request.getTag(),
            request.getPhotofile(),
            LocalDateTime.now()
        );
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long postId, PostUploadRequest request) {
        postRepository.save(setUpdatePost(postId, request));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Direction.DESC, "updatedAt"));
        return posts.stream().map(PostResponse::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {
        return new PostResponse(findPostById(postId));
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = findPostById(postId);
        postRepository.delete(post);
    }

    private Post setUpdatePost(Long postId, PostUploadRequest request) {
        Post post = findPostById(postId);

        post.setTitle(request.getTitle());
        post.setContents(request.getContents());
        post.setTag(request.getTag());
        post.setPhotofile(request.getPhotofile());
        post.setUpdatedAt(LocalDateTime.now());
        return post;
    }

    private Post findPostById(Long postId) {
        return postRepository.findByPostId(postId)
            .orElseThrow(() -> new IllegalArgumentException(PostState.NON_EXIST_POST.getMessage()));
    }
}
