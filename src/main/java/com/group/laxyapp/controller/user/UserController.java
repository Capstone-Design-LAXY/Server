package com.group.laxyapp.controller.user;

import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.request.UserUpdateRequest;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.service.user.UserService;
import com.group.laxyapp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<String> registUser(@RequestBody UserRegistRequest registRequest) {
        registRequest.setConfirmPassword(registRequest.getConfirmPassword());

        if (!registRequest.isPasswordMatching()) {
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
        }

        if (userService.isEmailDuplicate(registRequest.getEmail())) {
            return ResponseEntity.badRequest().body("중복된 이메일입니다. 다시 입력해주세요.");
        }

        userService.registUser(registRequest);

        return ResponseEntity.ok("회원 가입이 완료되었습니다.");
    }

    @GetMapping("/user")
    @ResponseBody
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/mypage")
    @ResponseBody
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateRequest updateRequest) {
        try {
            userService.updateUser(updateRequest);
            return ResponseEntity.ok("회원 정보가 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/user/delete")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@RequestParam("email") String email) {
        try {
            userService.deleteUser(email);
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check_email")
    @ResponseBody
    public boolean checkEmail(@RequestParam("email") String email) {
        return userService.isEmailDuplicate(email);
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest request, @RequestParam("refreshToken") String refreshToken) {
        // 리프레시 토큰 삭제
        authService.logout(refreshToken);

        // 세션 무효화
        new SecurityContextLogoutHandler().logout(request, null,
                SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.ok("로그아웃이 완료되었습니다.");
    }
}
