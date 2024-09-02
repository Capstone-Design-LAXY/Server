package com.group.laxyapp.dto.user.response;

import com.group.laxyapp.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponse {
    private final Long userId;
    private final String nickname;
    private String password;
    private final String email;
    private final LocalDate birth;
    private final String gender;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UserResponse(Long userId, String nickname, String gender, String email,
                        LocalDate birth, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.nickname = nickname;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.birth = user.getBirth();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    public long getId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public LocalDate getBirth() { return birth; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
