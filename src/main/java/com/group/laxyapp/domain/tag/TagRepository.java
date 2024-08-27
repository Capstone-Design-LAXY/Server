package com.group.laxyapp.domain.tag;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);

    @Query("SELECT SUM(tag.count) FROM tag")
    Integer findTotalCount();

    Optional<Tag> findByTagNameContains(String tag_name);
}
