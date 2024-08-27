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

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        logger.info("Encoded Password: " + encodedPassword);

        User user = new User(
                request.getNickname(),
                encodedPassword,
                request.getEmail(),
                request.getBirth(),
                request.getGender(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        logger.info("User created: " + user);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        if (users == null) {
            throw new IllegalArgumentException();
        }
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) throws IllegalArgumentException {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + request.getId()));

        user.setNickname(request.getNickname());
        user.setGender(request.getGender());
        user.setBirth(request.getBirth());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            user.setPassword(encodedPassword);
        }

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + nickname));
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
