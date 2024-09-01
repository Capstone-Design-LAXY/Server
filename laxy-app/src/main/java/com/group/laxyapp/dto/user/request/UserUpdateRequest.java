package com.group.laxyapp.dto.user.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserUpdateRequest {

    private Long user_id;
    private String nickname;
    private String password;
    private LocalDate birth;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getUser_id() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getGender() {
        return gender;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}