package com.sehbeomschool.nova.domain.game.exception;

import com.sehbeomschool.nova.global.error.exception.AlreadyExistException;

public class InProgressGameAlreadyExistException extends AlreadyExistException {

    public InProgressGameAlreadyExistException(String message) {
        super(message);
    }
}
