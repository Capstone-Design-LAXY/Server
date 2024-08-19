package com.group.laxyapp.controller;

import com.group.laxyapp.dto.user.request.UserLoginRequest;
import com.group.laxyapp.dto.user.response.TokenResponse;
import com.group.laxyapp.security.TokenProvider;
import com.group.laxyapp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
        try {
            String token = authService.authenticateAndGenerateToken(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(new TokenResponse(token, tokenProvider.createRefreshToken(loginRequest.getEmail())));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("로그인 실패: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, @RequestParam("refreshToken") String refreshToken) {
        authService.logout(refreshToken);
        new SecurityContextLogoutHandler().logout(request, null, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok("로그아웃이 완료되었습니다.");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        try {
            String newToken = authService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(new TokenResponse(newToken, refreshToken));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("토큰 갱신 실패: " + e.getMessage());
        }
    }
}
