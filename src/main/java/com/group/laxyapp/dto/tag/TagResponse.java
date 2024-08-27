package com.group.laxyapp.dto.tag;

import com.group.laxyapp.domain.tag.Tag;

import java.time.LocalDateTime;

public class TagResponse {
    public final Long tagId;
    public final String tagname;
    public final Integer count;
    public final Integer bookmarked;
    public final LocalDateTime createdAt;
    public final LocalDateTime updatedAt;
    public final Integer grade;

    public TagResponse(Tag tag) {
        this.tagId = tag.getTagId();
        this.tagname = tag.getTagName();
        this.count = tag.getCount();
        this.bookmarked = tag.getBookmarked();
        this.createdAt = tag.getCreatedAt();
        this.updatedAt = tag.getUpdatedAt();
        this.grade = tag.getGrade();
    }
}
