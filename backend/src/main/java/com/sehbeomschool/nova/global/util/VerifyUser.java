package com.sehbeomschool.nova.global.util;

import com.sehbeomschool.nova.global.error.constant.ExceptionMessage;
import com.sehbeomschool.nova.global.error.exception.NotVerifiedUserException;
import org.springframework.security.core.context.SecurityContextHolder;

public class VerifyUser {

    public static void verifyUser(Long userId) {
        Long curUserId = (Long) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
        if (!curUserId.equals(userId)) {
            throw new NotVerifiedUserException(ExceptionMessage.NOT_VARIFIED_USER.getMessage());
        }
    }
}
