package com.group.laxyapp.dto.user.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserUpdateRequest {

    private long id;
    private String nickname;
    private String password;
    private LocalDate birth;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public long getId() {
        return id;
    }

    public String getNickname() { return nickname; }

    public String getGender() { return gender; }

    public String getPassword() { return password; }

    public LocalDate getBirth() { return birth; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
