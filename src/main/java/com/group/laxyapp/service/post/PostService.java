package com.group.laxyapp.service.post;

import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.domain.user.UserRepository;
import com.group.laxyapp.dto.post.AllPostRequest;
import com.group.laxyapp.service.comment.CommentService;
import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.dto.post.PostRequest;
import com.group.laxyapp.dto.post.PostResponse;
import com.group.laxyapp.service.exception.CustomException;
import com.group.laxyapp.service.exception.enums.ErrorCode;
import com.group.laxyapp.service.exception.enums.ErrorMessage;
import com.group.laxyapp.service.tag.TagService;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;
    private final UserRepository userRepository;
    private final TagService TagService;
    private final static int MAX_TITLE_LENGTH = 30;

    @Transactional
    public Long uploadPost(PostRequest request) {
        Post post = Post.builder()
            .userId(request.getUserId())
            .title(request.getTitle())
            .contents(request.getContents())
            .tag(TagService.uploadTagByName(request.getTag()))
            .photoFile(request.getPhotoFile()).build();
        postRepository.save(post);
        return post.getPostId();
    }

    @Transactional
    public Long updatePost(Long postId, PostRequest request) {
        PostValidator.checkAuthorPost(request.getUserId(), findUserById(postId).getUserId());
        postRepository.save(setUpdatePost(postId, request));
        return postId;
    }

    @Transactional(readOnly = true)
    public List<AllPostRequest> getPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Direction.DESC, "createdAt"));
        return posts.stream().map(post -> AllPostRequest.builder()
            .title(subTitle(post.getTitle()))
            .contents(post.getContents())
            .createdAt(post.getCreatedAt())
            .commentCount(commentService.getCommentsByPostId(post.getPostId()).size())
            .likes(post.getLikes())
            .photoFile(post.getPhotoFile())
            .postId(post.getPostId()).build()
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {
        Post post = postRepository.getPostByPostId(postId);
        return PostResponse.builder()
            .likes(post.getLikes())
            .viewed(post.incrementViewed())
            .title(post.getTitle())
            .contents(post.getContents())
            .userNickname(findUserById(post.getUserId()).getNickname())
            .createAt(post.getCreatedAt())
            .tag(post.getTag()).build();
    }

    @Transactional
    public void deletePost(Long postId, PostRequest request) {
        PostValidator.checkAuthorPost(request.getUserId(), findUserById(postId).getUserId());
        postRepository.delete(postRepository.getPostByPostId(postId));
    }

    @Transactional
    public Long uploadLikes(Long postId) {
        return postRepository.getPostByPostId(postId).incrementLikes();
    }

    private Post setUpdatePost(Long postId, PostRequest request) {
        return postRepository.getPostByPostId(postId).toBuilder()
            .title(request.getTitle())
            .contents(request.getContents())
            .tag(updateTag(postId, request))
            .photoFile(request.getPhotoFile())
            .build();
    }

    private List<String> updateTag(Long postId, PostRequest request) {
        postRepository.findByPostId(postId)
            .ifPresent(post -> TagService.decrementTagsByName(post.getTag()));
        return TagService.uploadTagByName(request.getTag());
    }

    private String subTitle(String title) {
        if (title.length() <= MAX_TITLE_LENGTH) {
            return title;
        }
        return title.substring(0, MAX_TITLE_LENGTH);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(
                () -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,
                    ErrorMessage.NOT_FOUND_USER));
    }
}
