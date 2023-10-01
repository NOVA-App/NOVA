package com.sehbeomschool.nova.domain.game.exception;

public class GameFinishedException extends IllegalArgumentException {

    public GameFinishedException(String message) {
        super(message);
    }

}
