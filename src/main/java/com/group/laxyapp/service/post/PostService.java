package com.group.laxyapp.service.post;

import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.domain.user.UserRepository;
import com.group.laxyapp.dto.post.request.PostUploadRequest;
import com.group.laxyapp.dto.post.response.PostResponse;
import com.group.laxyapp.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public PostResponse uploadPost(String token, PostUploadRequest request) {
        Long userId = getUserIdFromToken(token);

        Post post = Post.builder()
                .userId(userId)
                .title(request.getTitle())
                .contents(request.getContents())
                .tag(request.getTag())
                .photoFile(request.getPhotoFile())
                .build();
        return new PostResponse(postRepository.save(post));
    }

    @Transactional
    public PostResponse updatePost(String token, Long postId, PostUploadRequest request) {
        Long userId = getUserIdFromToken(token);

        // ID로 게시물을 찾음
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        // 게시물의 소유자 확인
        if (!post.getUserId().equals(userId)) {
            throw new IllegalArgumentException("이 게시물을 업데이트할 권한이 없습니다.");
        }

        // 게시물 업데이트
        Post updatedPost = setUpdatePost(post, request);

        // 디버깅을 위한 로그 추가
        System.out.println("Saving Updated Post: " + updatedPost);

        // 업데이트된 게시물 저장
        return new PostResponse(postRepository.save(updatedPost));
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
            throw new IllegalArgumentException("이 게시물을 삭제할 권한이 없습니다.");
        }

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(PostResponse::new)
                .collect(Collectors.toList());
    }

    public Long getUserIdFromToken(String token) {
        String jwtToken = token.substring(7); // "Bearer "을 제거
        return tokenProvider.getUserIdFromToken(jwtToken); // JWT에서 사용자 ID 추출
    }

    private Post setUpdatePost(Post post, PostUploadRequest request) {
        return post.toBuilder()
                .title(request.getTitle())
                .contents(request.getContents())
                .tag(request.getTag())
                .photoFile(request.getPhotoFile())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
    }
}
