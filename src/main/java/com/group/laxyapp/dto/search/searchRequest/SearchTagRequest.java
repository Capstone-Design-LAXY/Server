package com.group.laxyapp.dto.search.searchRequest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchTagRequest {

    private final String tagname;
}
