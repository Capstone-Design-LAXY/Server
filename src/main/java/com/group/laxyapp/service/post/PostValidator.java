package com.group.laxyapp.service.post;

import com.group.laxyapp.service.exception.CustomException;
import com.group.laxyapp.service.exception.enums.ErrorCode;
import com.group.laxyapp.service.exception.enums.ErrorMessage;
import org.springframework.stereotype.Component;

@Component
public class PostValidator {

    public static void checkAuthorPost(Long userId, Long writerId) {
        if (!isSameUserId(userId, writerId)) {
            throw new CustomException(ErrorCode.FORBIDDEN, ErrorMessage.FORBIDDEN_DELETE_POST);
        }
    }

    public static boolean isSameUserId(Long userId, Long writerId) {
        return userId.equals(writerId);
    }
}
