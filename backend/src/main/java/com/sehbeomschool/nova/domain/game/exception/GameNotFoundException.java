package com.sehbeomschool.nova.domain.game.exception;

import com.sehbeomschool.nova.global.error.exception.NotFoundException;

public class GameNotFoundException extends NotFoundException {

    public GameNotFoundException(String message) {
        super(message);
    }
}
