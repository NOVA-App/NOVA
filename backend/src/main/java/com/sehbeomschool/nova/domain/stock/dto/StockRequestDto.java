package com.sehbeomschool.nova.domain.stock.dto;

import lombok.Builder;
import lombok.Getter;

public class StockRequestDto {

    @Getter
    public static class TradeStockRequestDto {

        private Long gameId;
        private Long stockId;
        private Long purchaseAmount;

        @Builder
        public TradeStockRequestDto(Long gameId, Long stockId, Long purchaseAmount) {
            this.gameId = gameId;
            this.stockId = stockId;
            this.purchaseAmount = purchaseAmount;
        }
    }
}
