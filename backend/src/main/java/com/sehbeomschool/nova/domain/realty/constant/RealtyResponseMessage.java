package com.sehbeomschool.nova.domain.realty.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RealtyResponseMessage {
    READ_MY_REATIES("내 부동산 보유 현황 반환 완료");

    private final String message;
}
