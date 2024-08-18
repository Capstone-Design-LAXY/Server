package com.group.laxyapp.dto.user.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginRequest {
    private final String email;
    private final String password;
}
