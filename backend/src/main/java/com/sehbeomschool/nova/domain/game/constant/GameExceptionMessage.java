package com.sehbeomschool.nova.domain.game.constant;

public enum GameExceptionMessage {
    GAME_NOT_FOUND("해당 게임 미존재");

    private final String message;

    GameExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
