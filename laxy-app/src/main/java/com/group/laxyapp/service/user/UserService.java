package com.group.laxyapp.service.user;

import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.domain.user.UserRepository;
import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.request.UserUpdateRequest;
import com.group.laxyapp.dto.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + email));
    }

    @Transactional
    public void registUser(UserRegistRequest request) {
        if (!request.isPasswordMatching()) {
            throw new IllegalArgumentException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이메일이 이미 사용 중입니다: " + request.getEmail());
        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        logger.info("Encoded Password: " + encodedPassword);

        User user = User.builder()
                .nickname(request.getNickname())
                .password(encodedPassword)
                .email(request.getEmail())
                .birth(request.getBirth())
                .gender(request.getGender())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        logger.info("User created: " + user);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        if (users == null || users.isEmpty()) {
            throw new IllegalArgumentException("사용자 목록이 비어 있습니다.");
        }
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(String email, UserUpdateRequest request) throws IllegalArgumentException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + email));

        User updatedUser = user.toBuilder()
                .nickname(request.getNickname())
                .gender(request.getGender())
                .birth(request.getBirth())
                .updatedAt(LocalDateTime.now())
                .build();

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            updatedUser = updatedUser.toBuilder().password(encodedPassword).build();
        }

        userRepository.save(updatedUser);
    }

    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + email));
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
