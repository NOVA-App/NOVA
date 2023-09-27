package com.sehbeomschool.nova.domain.realty.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RealtyResponseMessage {
    READ_MY_REALTIES("내 부동산 보유 현황 반환 완료"),
    READ_MY_REALTY_DETAIL("내 부동산 상세 정보 반환 완료"),
    READ_REALTIES("부동산 목록 반환 완료"),
    READ_REALTY("부동산 매물 상세 정보 반환 완료"),
    BUY_REALTY("부동산 매수 완료"),
    SELL_REALTY("부동산 매도 완료");

    private final String message;
}
