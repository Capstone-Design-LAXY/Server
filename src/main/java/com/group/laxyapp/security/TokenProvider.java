package com.group.laxyapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
@Getter
public class TokenProvider {

    private final Key key;
    private final long tokenValidityInSeconds;
    private final long refreshTokenValidityInSeconds;

    public TokenProvider(String secretKey) {
        this.key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        this.tokenValidityInSeconds = 3600;
        this.refreshTokenValidityInSeconds = 604800;
    }

    public String createToken(String email, Long userId) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInSeconds * 1000);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public String createRefreshToken(String email, Long userId) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInSeconds * 1000);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getClaims(token);
        return Long.parseLong(claims.get("userId").toString());
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims;
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        String email = claims.getSubject();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(email, "", Collections.singleton(authority));
        return new UsernamePasswordAuthenticationToken(userDetails, token, Collections.singleton(authority));
    }
}