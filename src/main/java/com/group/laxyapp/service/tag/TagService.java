package com.group.laxyapp.service.tag;

import com.group.laxyapp.domain.tag.Tag;
import com.group.laxyapp.domain.tag.TagRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public List<String> uploadTagByName(List<String> names) {
        return names.stream()
            .map(name -> {
                Tag tag = tagRepository.findByTagName(name)
                    .map(existingTag ->
                        tagRepository.save(existingTag.incrementCount()))
                    .orElseGet(() -> tagRepository.save(Tag.builder().tagName(name).build()));
                return tag.getTagName();
            })
            .collect(Collectors.toList());
    }
}
