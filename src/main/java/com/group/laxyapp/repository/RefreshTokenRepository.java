package com.group.laxyapp.repository;

import com.group.laxyapp.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token_id);
    Optional<RefreshToken> findByEmail(String email);
    void deleteByToken(String refresh_token);
}
