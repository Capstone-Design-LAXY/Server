package com.group.laxyapp.domain.post;

import com.group.laxyapp.service.exception.CustomException;
import com.group.laxyapp.service.exception.enums.ErrorCode;
import com.group.laxyapp.service.exception.enums.ErrorMessage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);

    default Post getPostByPostId(Long postId) {
        return findByPostId(postId)
            .orElseThrow(
                () -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,
                    ErrorMessage.NOT_FOUND_POST));
    }
}
