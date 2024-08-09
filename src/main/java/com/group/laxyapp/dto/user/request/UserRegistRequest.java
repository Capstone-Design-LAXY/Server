package com.group.laxyapp.dto.user.request;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRegistRequest {

    private String nickname;
    private String password;
    private String confirmPassword;
    private String email;
    private LocalDate birth;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getNickname() {
        return nickname;
    }

    public String getPassword() { return password; }

    public String getConfirmPassword() { return confirmPassword; }

    public String getGender() { return gender; }

    public String getEmail() { return email; }

    public LocalDate getBirth() { return birth; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if(password == null || password.isBlank()) {
            throw new IllegalArgumentException(String.format("비밀번호 존재하지않음."));
        }
        this.password = passwordEncoder.encode(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword);
    }
}
