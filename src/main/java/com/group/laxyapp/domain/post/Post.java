package com.group.laxyapp.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "user_id", nullable = false, length = 255)
    private Long userId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Convert(converter = StringListConverter.class)
    @Column(name = "tag", columnDefinition = "JSON")
    private List<String> tag;

    @Convert(converter = StringListConverter.class)
    @Column(name = "photo_file", columnDefinition = "JSON")
    private List<String> photoFile;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "likes", columnDefinition = "BIGINT DEFAULT 0")
    private Long likes = 0L;

    @Column(name = "viewed", columnDefinition = "BIGINT DEFAULT 0")
    private Long viewed = 0L;

    public Post(Long userId, String title, String contents, List<String> tag, List<String> photoFile
        ,LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.tag = tag;
        this.photoFile = photoFile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Post increaseViewed() {
        this.viewed++;
        return this;
    }
}
