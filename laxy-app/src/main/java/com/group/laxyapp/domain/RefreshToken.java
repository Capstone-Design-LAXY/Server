package com.group.laxyapp.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(name = "refresh_token", nullable = false, length = 255)
    private String token;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Builder
    public RefreshToken(String token, String email, Date expiryDate) {
        this.token = token;
        this.email = email;
        this.expiryDate = expiryDate;
    }

    public RefreshToken updateToken(String token, Date expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;
        return this;
    }
}
