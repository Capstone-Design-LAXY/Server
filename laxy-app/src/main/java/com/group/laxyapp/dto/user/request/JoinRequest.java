package com.group.laxyapp.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    private String email;
    private String password;
    private String passwordCheck;
    private String nickname;
}
