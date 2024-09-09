package com.group.laxyapp.security;

import com.group.laxyapp.service.exception.enums.ErrorCode;
import com.group.laxyapp.service.exception.enums.ErrorMessage;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String token = resolveToken(request);
        checkTokenValid(response, token);

        Authentication authentication = tokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void checkTokenValid(HttpServletResponse response, String token) {
        if (!tokenProvider.validateToken(token)) {
            if (token == null) {
                sendError(response, ErrorMessage.UNAUTHORIZED_LOGIN);
                return;
            }
            sendError(response, ErrorMessage.UNAUTHORIZED_TOKEN);
            return;
        }
    }

    @SneakyThrows
    private void sendError(HttpServletResponse response, ErrorMessage message) {
        response.setStatus(ErrorCode.UNAUTHORIZED.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().format("{\"errorCode\": \"%s\", \"errorMessage\": \"%s\"}"
            , ErrorCode.UNAUTHORIZED.getHttpStatus().value()
            , message.getMessage());
        response.flushBuffer();
        return;
    }
}