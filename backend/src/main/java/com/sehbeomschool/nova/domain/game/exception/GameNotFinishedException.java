package com.sehbeomschool.nova.domain.game.exception;

public class GameNotFinishedException extends IllegalArgumentException {

    public GameNotFinishedException(String message) {
        super(message);
    }
}
