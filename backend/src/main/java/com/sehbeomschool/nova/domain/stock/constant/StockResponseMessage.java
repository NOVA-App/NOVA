package com.sehbeomschool.nova.domain.stock.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StockResponseMessage {
    READ_STOCKS_LIST("주식 종목 반환"),
    READ_STOCK_DETAIL("주식 종목 상세 반환 완료");

    private final String message;
    
}
