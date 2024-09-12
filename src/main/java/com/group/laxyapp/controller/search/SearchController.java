package com.group.laxyapp.controller.search;

import com.group.laxyapp.dto.post.PostResponse;
import com.group.laxyapp.dto.search.searchRequest.SearchPostRequest;
import com.group.laxyapp.dto.search.searchRequest.SearchTagRequest;
import com.group.laxyapp.dto.search.searchRequest.SearchUserRequest;
import com.group.laxyapp.dto.tag.TagResponse;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search/user")
    @ResponseBody
    public ResponseEntity<List<UserResponse>> userSearch(@RequestBody SearchUserRequest request) {
        return ResponseEntity.ok(searchService.userSearch(request));
    }

    @GetMapping("/search/post")
    @ResponseBody
    public ResponseEntity<List<PostResponse>> titleSearch(@RequestBody SearchPostRequest request) {
        return ResponseEntity.ok(searchService.titleSearch(request));
    }

    @GetMapping("/search/tag")
    @ResponseBody
    public ResponseEntity<List<TagResponse>> tagSearch(@RequestBody SearchTagRequest request) {
        return ResponseEntity.ok(searchService.tagSearch(request));
    }

//    @GetMapping("/search/community")
//    @ResponseBody
//    public ResponseEntity<List>
}
