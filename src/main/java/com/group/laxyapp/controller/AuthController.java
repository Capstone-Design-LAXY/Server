package com.group.laxyapp.controller;

import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.dto.user.request.UserLoginRequest;
import com.group.laxyapp.dto.user.response.TokenResponse;
import com.group.laxyapp.security.TokenProvider;
import com.group.laxyapp.service.AuthService;
import com.group.laxyapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest request) {
        User user = userService.loadUserByUsername(request.getEmail());

        if (userService.checkPassword(request.getPassword(), user.getPassword())) {
            String accessToken = tokenProvider.createToken(user.getEmail());
            String refreshToken = tokenProvider.createRefreshToken(user.getEmail());
            return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        authService.logout(token);
        return ResponseEntity.noContent().build();
    }
}
