package com.group.laxyapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.group.laxyapp.security.TokenProvider;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public TokenProvider tokenProvider() {
        return new TokenProvider(secretKey);
    }

}