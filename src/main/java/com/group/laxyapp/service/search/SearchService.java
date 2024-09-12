package com.group.laxyapp.service.search;

import com.group.laxyapp.domain.post.PostRepository;
import com.group.laxyapp.domain.tag.TagRepository;
import com.group.laxyapp.dto.search.searchRequest.SearchPostRequest;
import com.group.laxyapp.dto.search.searchRequest.SearchTagRequest;
import com.group.laxyapp.dto.search.searchRequest.SearchUserRequest;
import com.group.laxyapp.dto.tag.TagResponse;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.domain.user.UserRepository;
import com.group.laxyapp.dto.post.PostResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    @Transactional
    public List<UserResponse> userSearch(SearchUserRequest request) {
        List<User> users = userRepository.findByNicknameContains(request.getNickname());

        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PostResponse> titleSearch(SearchPostRequest request) {
        return postRepository.findByTitleContains(request.getTitle())
                .stream().map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TagResponse> tagSearch(SearchTagRequest request) {
        //Optional<List<TagResponse>> tagList = tagRepository.findByTagNameContains(request.getTagname());

        return tagRepository.findByTagNameContains(request.getTagname())
                .stream().map(TagResponse::new)
                .collect(Collectors.toList());
    }
}
