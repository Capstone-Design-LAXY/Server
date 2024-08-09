package com.group.laxyapp.service;

import com.group.laxyapp.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;

    public void logout(String refreshToken) {
        // 리프레시 토큰 삭제
        refreshTokenService.deleteByToken(refreshToken);
    }
}
