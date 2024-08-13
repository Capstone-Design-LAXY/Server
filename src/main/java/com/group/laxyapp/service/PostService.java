package com.group.laxyapp.service;

import com.group.laxyapp.domain.post.Post;
import com.group.laxyapp.domain.post.PostRepository;
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
}
