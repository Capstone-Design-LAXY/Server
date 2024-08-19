package com.group.laxyapp.service;

import com.group.laxyapp.domain.RefreshToken;
import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.domain.user.UserRepository;
import com.group.laxyapp.repository.RefreshTokenRepository;
import com.group.laxyapp.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public String authenticateAndGenerateToken(String email, String password) {
        // 인증 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 사용자 ID 직접 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Long userId = user.getUserId();

        // Access Token 생성
        String accessToken = tokenProvider.createToken(email, userId);

        // Refresh Token 생성 및 저장
        String refreshToken = tokenProvider.createRefreshToken(email, userId);
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .expiryDate(new Date(System.currentTimeMillis() + tokenProvider.getRefreshTokenValidityInSeconds() * 1000))
                .build();
        refreshTokenRepository.save(refreshTokenEntity);

        return accessToken;
    }

    public void logout(String refreshToken) {
        // 리프레시 토큰 삭제
        refreshTokenRepository.deleteByToken(refreshToken);
    }

    public String refreshAccessToken(String refreshToken) {
        // 리프레시 토큰을 이용해 새 액세스 토큰을 생성
        return refreshTokenRepository.findByToken(refreshToken)
                .map(token -> {
                    if (token.getExpiryDate().before(new Date())) {
                        throw new RuntimeException("Refresh token expired");
                    }
                    // 사용자 ID를 포함하여 새 액세스 토큰 생성
                    User user = userRepository.findByEmail(token.getEmail())
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    return tokenProvider.createToken(token.getEmail(), user.getUserId());
                })
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
    }
}
