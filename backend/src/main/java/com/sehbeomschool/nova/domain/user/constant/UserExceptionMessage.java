package com.sehbeomschool.nova.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionMessage {

    NOT_EXIST_USER("존재하지 않는 유저입니다");
    
    private final String message;
}
