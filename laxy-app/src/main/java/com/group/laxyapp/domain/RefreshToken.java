package com.group.laxyapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;

    @Column(name = "refresh_token", nullable = false, length = 255)
    private String token;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;
}
