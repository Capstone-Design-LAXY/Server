package com.group.laxyapp.service.post;

import com.group.laxyapp.service.exception.CustomException;
import com.group.laxyapp.service.exception.enums.ErrorCode;
import com.group.laxyapp.service.exception.enums.ErrorMessage;
import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.dto.post.PostRequest;
import com.group.laxyapp.dto.post.PostResponse;
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
    private final TagService TagService;

    @Transactional
    public PostResponse uploadPost(PostRequest request) {
        Post post = Post.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .contents(request.getContents())
                .tag(TagService.uploadTagByName(request.getTag()))
                .photoFile(request.getPhotoFile()).build();
        return new PostResponse(postRepository.save(post));
    }

    @Transactional
    public PostResponse updatePost(Long postId, PostRequest request) {
        PostValidator.checkAuthorPost(request.getUserId(), getWriterIdByPostId(postId));
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
        return new PostResponse(postRepository.save(getPostByPostId(postId).incrementViewed()));
    }

    @Transactional
    public void deletePost(Long postId, PostRequest request) {
        PostValidator.checkAuthorPost(request.getUserId(), getWriterIdByPostId(postId));
        postRepository.delete(getPostByPostId(postId));
    }

    private Post setUpdatePost(Long postId, PostRequest request) {
        return getPostByPostId(postId).toBuilder()
                .title(request.getTitle())
                .contents(request.getContents())
                .tag(updateTag(postId, request))
                .photoFile(request.getPhotoFile())
                .build();
    }

    private Long getWriterIdByPostId(Long postId) {
        return getPostByPostId(postId).getUserId();
    }

    private Post getPostByPostId(Long postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,
                                ErrorMessage.NOT_FOUND_POST));
    }

    private List<String> updateTag(Long postId, PostRequest request) {
        postRepository.findByPostId(postId)
                .ifPresent(post -> TagService.decrementTagsByName(post.getTag()));
        return TagService.uploadTagByName(request.getTag());
    }
}