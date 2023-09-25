package com.sehbeomschool.nova.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserResponseMessage {
    LOGIN_SUCCESS("로그인 성공"),
    LOGIN_FAIL("로그인 실패"),
    ACCESS_TOKEN_EXPIRED("토큰 만료됨");

    private final String message;

}
