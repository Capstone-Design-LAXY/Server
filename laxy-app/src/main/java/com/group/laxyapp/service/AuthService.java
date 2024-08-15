package com.group.laxyapp.service;

import com.group.laxyapp.domain.RefreshToken;
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

    public String authenticateAndGenerateToken(String email, String password) {
        // 인증 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Access Token 생성
        String accessToken = tokenProvider.createToken(email);

        // Refresh Token 생성 및 저장
        String refreshToken = tokenProvider.createRefreshToken(email);
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setEmail(email);
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setExpiryDate(new Date(System.currentTimeMillis() + tokenProvider.getRefreshTokenValidityInSeconds() * 1000));
        refreshTokenRepository.save(refreshTokenEntity);

        return accessToken; // 또는 accessToken과 refreshToken을 함께 반환할 수 있습니다.
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
                    return tokenProvider.createToken(token.getEmail());
                })
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
    }
}
