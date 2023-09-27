package com.sehbeomschool.nova.domain.game.constant;

public enum GameResponseMessage {
    GAME_START_SUCCESS("게임 시작 완료"),
    READ_CURRENT_YEAR_SUCCESS("현재 해 정보 조회 완료"),
    UPDATE_LIVING_COST_SUCCESS("생활비 수정 완료"),
    READ_FIXED_COST_SUCCESS("고정 지출 내역 조회 완료"),
    MARRY_SUCCESS("결혼 완료");

    private final String message;

    GameResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
