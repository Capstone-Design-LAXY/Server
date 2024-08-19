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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Convert(converter = StringListConverter.class)
    @Column(name = "tag", columnDefinition = "JSON")
    private List<String> tag;

    @Convert(converter = StringListConverter.class)
    @Column(name = "photo_file", columnDefinition = "JSON")
    private List<String> photoFile;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "likes", columnDefinition = "BIGINT DEFAULT 0")
    private Long likes;

    @Column(name = "viewed", columnDefinition = "BIGINT DEFAULT 0")
    private Long viewed;

    @Builder
    public Post(Long userId, String title, String contents, List<String> tag, List<String> photoFile) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.tag = tag;
        this.photoFile = photoFile;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.likes = 0L;
        this.viewed = 0L;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Post increaseViewed() {
        this.viewed++;
        return this;
    }
}
