package com.group.laxyapp.controller.post;

import com.group.laxyapp.domain.enums.PostState;
import com.group.laxyapp.domain.post.Post;
import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostControllerTest {

    @DisplayName("게시글 내용이 공백이라면 예외 처리")
    @Test
    public void uploadContentsBlank() {
        try {
            new Post(1L,"title", "", Collections.singletonList("tag"),
                Collections.singletonList("photofile"), LocalDateTime.now());
        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals(exception.getMessage(), PostState.NULL_CONTENT.getMessage());
        }
    }

    @DisplayName("게시글 제목이 공백이라면 예외 처리")
    @Test
    public void uploadTitleBlank() {
        try {
            new Post(1L, "", "content", Collections.singletonList("tag"),
                Collections.singletonList("photofile"),LocalDateTime.now());
        } catch (IllegalArgumentException exception) {
            Assertions.assertEquals(exception.getMessage(), PostState.NULL_TITLE.getMessage());
        }
    }
}