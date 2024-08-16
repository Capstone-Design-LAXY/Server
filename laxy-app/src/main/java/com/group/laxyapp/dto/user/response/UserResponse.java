package com.group.laxyapp.dto.user.response;

import com.group.laxyapp.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponse {
    private long user_id;
    private String nickname;
    private String password;
    private String email;
    private LocalDate birth;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponse(long user_id, String nickname, String gender, String email,
                        LocalDate birth, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.gender = gender;
        this.email = email;
        this.birth = birth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserResponse(User user) {
        this.user_id = user.getUser_id();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.birth = user.getBirth();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    public long getUser_id() {
        return user_id;
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
