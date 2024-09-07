package com.group.laxyapp.dto.post;


import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostResponse {
    private final Long likes;
    private final Long viewed;
    private final String title;
    private final String contents;
    private final String userNickname;
    private final List<String> photoFile;
    private final LocalDateTime createAt;
    private final LocalDateTime updatedAt;
    private final List<String> tag;
}
