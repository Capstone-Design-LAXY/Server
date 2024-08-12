package com.group.laxyapp.service;

import com.group.laxyapp.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public String authenticateAndGenerateToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(email);
    }

    public void logout(String refreshToken) {
        // 리프레시 토큰 삭제 로직 구현
    }

    public String refreshAccessToken(String refreshToken) {
        // 리프레시 토큰을 이용해 새 액세스 토큰을 생성하는 로직 구현
        return null;
    }
}
