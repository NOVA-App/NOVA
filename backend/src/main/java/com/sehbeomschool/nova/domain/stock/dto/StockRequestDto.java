package com.sehbeomschool.nova.domain.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockRequestDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TradeStockRequestDto {

        private Long gameId;
        private Long stockId;
        private Long purchaseAmount;
    }
}
