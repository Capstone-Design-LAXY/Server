package com.group.laxyapp.dto.user.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinRequest {
    private final String email;
    private final String password;
    private final String passwordCheck;
    private final String nickname;
}