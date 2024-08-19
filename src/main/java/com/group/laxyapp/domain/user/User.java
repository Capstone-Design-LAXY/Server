package com.group.laxyapp.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "nick_name", nullable = false, length = 20)
    private String nickname;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "pass_word", nullable = false, length = 60)
    private String password;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder(toBuilder = true)
    public User(String nickname, String password, String email, LocalDate birth, String gender,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public User updateUserInfo(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public User changePassword(String password) {
        this.password = password;
        return this;
    }

    public Long getUserId() {
        return userId;
    }
}
