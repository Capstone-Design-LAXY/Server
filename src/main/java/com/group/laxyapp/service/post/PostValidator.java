package com.group.laxyapp.service.post;

import com.group.laxyapp.service.exception.CustomException;
import com.group.laxyapp.service.exception.enums.ErrorCode;
import com.group.laxyapp.service.exception.enums.ErrorMessage;
import org.springframework.stereotype.Component;

@Component
public class PostValidator {

    public static void checkDeletePostById(Long userId, Long writerId) {
        if (!isSameUserId(userId, writerId)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR, ErrorMessage.UNAUTHORIZED_DELETE_POST);
        }
    }

    public static boolean isSameUserId(Long userId, Long writerId) {
        return userId.equals(writerId);
    }
}
