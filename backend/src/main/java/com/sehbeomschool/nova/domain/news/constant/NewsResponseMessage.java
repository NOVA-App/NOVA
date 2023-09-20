package com.sehbeomschool.nova.domain.news.constant;

public enum NewsResponseMessage {
    READ_NEWS("뉴스 조회 완료");

    private final String message;

    NewsResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
