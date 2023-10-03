package com.sehbeomschool.nova.domain.user.exception;

import com.sehbeomschool.nova.global.error.exception.AlreadyExistException;

public class UserExistException extends AlreadyExistException {

    public UserExistException(String message) {
        super(message);
    }
}
