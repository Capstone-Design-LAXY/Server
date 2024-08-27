package com.group.laxyapp.controller.search;

import com.group.laxyapp.dto.post.PostResponse;
import com.group.laxyapp.dto.tag.TagResponse;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search/{nickname}")
    @ResponseBody
    public ResponseEntity<List<UserResponse>> userSearch(@PathVariable("nickname") String nickname) {
        return ResponseEntity.ok(searchService.userSearch(nickname));
    }

    @GetMapping("/search/{title}")
    @ResponseBody
    public ResponseEntity<List<PostResponse>> titleSearch(@PathVariable("title") String title) {
        return ResponseEntity.ok(searchService.titleSearch(title));
    }

    @GetMapping("/search/{tag_name}")
    @ResponseBody
    public ResponseEntity<List<TagResponse>> tagSearch(@PathVariable("tag_name") String tag_name) {
        return ResponseEntity.ok(searchService.tagSearch(tag_name));
    }
}
