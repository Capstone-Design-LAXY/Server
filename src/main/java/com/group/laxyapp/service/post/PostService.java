package com.group.laxyapp.service.post;

import com.group.laxyapp.controller.exception.CommonException;
import com.group.laxyapp.controller.exception.CommonErrorCode;
import com.group.laxyapp.controller.exception.ErrorMessage;
import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.dto.post.PostRequest;
import com.group.laxyapp.dto.post.PostResponse;
import java.util.stream.Collectors;

import com.group.laxyapp.security.TokenProvider;
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
    private final TokenProvider tokenProvider;

    @Transactional
    public PostResponse uploadPost(String token, PostRequest request) {
        Long userId = getUserIdFromToken(token);

        Post post = Post.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .contents(request.getContents())
                .tag(request.getTag())
                .photoFile(request.getPhotoFile())
                .build();
        return new PostResponse(postRepository.save(post));
    }

    public Long getUserIdFromToken(String token) {
        String jwtToken = token.substring(7);
        return tokenProvider.getUserIdFromToken(jwtToken);
    }

    @Transactional
    public PostResponse updatePost(String token, Long postId, PostRequest request) {
        Long userId = getUserIdFromToken(token);

        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new CommonException(
                        CommonErrorCode.DISPLAY_NOT_FOUND,
                        ErrorMessage.DISPLAY_NOT_FOUND.getMessage()));

        if (!post.getUserId().equals(userId)) {
            throw new CommonException(
                    CommonErrorCode.UNAUTHORIZED_ERROR,
                    ErrorMessage.POST_UPDATE_ERROR.getMessage());
        }

        return new PostResponse(postRepository.save(setUpdatePost(post, request)));
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
    public void deletePost(String token, Long postId) {
        Long userId = getUserIdFromToken(token);
        Post post = findPostById(postId);

        if (!post.getUserId().equals(userId)) {
            throw new CommonException(
                    CommonErrorCode.POST_DELETE_ERROR,
                    ErrorMessage.POST_DELETE_ERROR.getMessage());
        }

        postRepository.delete(post);
    }

    private Post setUpdatePost(Post post, PostRequest request) {
        return post.toBuilder()
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

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(PostResponse::new)
                .collect(Collectors.toList());
    }
}