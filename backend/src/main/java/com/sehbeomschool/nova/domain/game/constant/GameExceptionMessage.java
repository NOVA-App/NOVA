package com.sehbeomschool.nova.domain.game.constant;

public enum GameExceptionMessage {
    GAME_NOT_FOUND("해당 게임 미존재"),
    USABLE_ASSET_NOT_ENOUGH("여유 자금 부족");

    private final String message;

    GameExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
