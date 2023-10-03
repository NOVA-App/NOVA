package com.sehbeomschool.nova.global.util;

import com.sehbeomschool.nova.global.error.constant.ExceptionMessage;
import com.sehbeomschool.nova.global.error.exception.NotVarifiedUserException;
import org.springframework.security.core.context.SecurityContextHolder;

public class VarifyUser {

    public static void varifyUser(Long userId) {
        Long curUserId = (Long) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
        if (!curUserId.equals(userId)) {
            throw new NotVarifiedUserException(ExceptionMessage.NOT_VARIFIED_USER.getMessage());
        }
    }
}
