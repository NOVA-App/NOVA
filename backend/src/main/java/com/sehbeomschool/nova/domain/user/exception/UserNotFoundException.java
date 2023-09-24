package com.sehbeomschool.nova.domain.user.exception;

import com.sehbeomschool.nova.global.error.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
