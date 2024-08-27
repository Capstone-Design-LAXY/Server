package com.group.laxyapp.service.search;

import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.domain.tag.TagRepository;
import com.group.laxyapp.dto.tag.TagResponse;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.domain.user.UserRepository;
import com.group.laxyapp.dto.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public SearchService(UserRepository userRepository, PostRepository postRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public List<UserResponse> userSearch(String nickname) {
        List<User> users = userRepository.findByNicknameContains(nickname);

        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public List<PostResponse> titleSearch(String title) {
        return postRepository.findByTitleContains(title)
                .stream().map(PostResponse::new)
                .collect(Collectors.toList());
    }

    public List<TagResponse> tagSearch(String tag_name) {
        return tagRepository.findByTagNameContains(tag_name)
                .stream().map(TagResponse::new)
                .collect(Collectors.toList());
    }
}
