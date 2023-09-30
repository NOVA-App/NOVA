package com.sehbeomschool.nova.domain.game.constant;

public enum GameExceptionMessage {
    GAME_NOT_FOUND("해당 게임 미존재"),
    IN_PROGRESS_GAME_NOT_FOUND("진행 중 게임 조회 실패"),
    IN_PROGRESS_GAME_ALREADY_EXIST("진행 중 게임 존재"),
    GAME_FINISHED("종료된 게임"),
    USABLE_ASSET_NOT_ENOUGH("여유 자금 부족");

    private final String message;

    GameExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
