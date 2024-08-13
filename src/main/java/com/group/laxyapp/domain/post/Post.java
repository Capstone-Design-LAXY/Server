package com.group.laxyapp.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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

    @Column(name = "id", nullable = false, length = 255)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "tag", length = 255)
    private String tag;

    @Column(name = "photofile", length = 255)
    private String photofile;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    public Post(Long id, String title, String contents, String tag, String photofile, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.tag = tag;
        this.photofile = photofile;
        this.updatedAt = updatedAt;
    }
}
