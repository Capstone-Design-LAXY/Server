package com.group.laxyapp.service;

import com.group.laxyapp.domain.enums.PostState;
import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.dto.post.request.PostUpdateRequest;
import com.group.laxyapp.dto.post.request.PostUploadRequest;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
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
            request.getTags(),
            request.getPhotoFile(),
            LocalDateTime.now()
        );

        postRepository.save(post);
    }

    @Transactional
    public void updatePost(PostUpdateRequest request) throws IllegalArgumentException {
        Post post = postRepository.findByPostId(request.getPostId())
            .orElseThrow(() -> new IllegalArgumentException(PostState.NON_EXIST_POST.getMessage()));

        post.setId(request.getId());
        post.setTitle(request.getTitle());
        post.setContents(request.getContents());
        post.setTag(request.getTag());
        post.setPhotofile(request.getPhotofile());
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);
    }
}
