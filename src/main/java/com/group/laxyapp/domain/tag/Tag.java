package com.group.laxyapp.domain.tag;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @Column(name = "count", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer count;

    @Column(name = "bookmarked", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer bookmarked;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "grade", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer grade;

    @PrePersist
    protected void onCreate() {
        this.count = 1;
        this.bookmarked = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.grade = 1;
    }

    public Tag incrementCount() {
        this.count++;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public Tag decrementCount() {
        this.count--;
        this.updatedAt = LocalDateTime.now();
        return this;
    }
}