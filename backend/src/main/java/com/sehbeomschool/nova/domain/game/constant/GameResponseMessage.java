package com.sehbeomschool.nova.domain.game.constant;

public enum GameResponseMessage {
    GAME_START_SUCCESS("게임 시작 완료"),
    NEXT_YEAR_UPDATE_SUCCESS("다음 해 정보 업데이트 완료"),
    GAME_FINISHED_SUCCESS("게임 종료"),
    READ_CURRENT_YEAR_SUCCESS("현재 해 정보 조회 완료"),
    UPDATE_LIVING_COST_SUCCESS("생활비 수정 완료"),
    READ_FIXED_COST_SUCCESS("고정 지출 내역 조회 완료"),
    GIVE_UP_GAME_SUCCESS("게임 포기 처리 완료"),
    READ_GAME_RESULT_DETAIL_SUCCESS("게임 결과 상세 조회 완료"),
    READ_ALL_MY_GAMES_SUCCESS("결과 목록 조회 완료"),
    READ_RANKING_LIST_SUCCESS("랭킹 목록 조회 완료"),
    MARRY_SUCCESS("결혼 완료");

    private final String message;

    GameResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
