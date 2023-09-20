package com.sehbeomschool.nova.domain.stock.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class StockResponseDto {

    @Getter
    public static class readStocksListResponseDto {
        private Long stockId;
        private String stockName;
        private Long evaluationAmount;
        private Long fluctuations;

        @Builder
        public readStocksListResponseDto(Long stockId, String stockName, Long evaluationAmount,
            Long fluctuations) {
            this.stockId = stockId;
            this.stockName = stockName;
            this.evaluationAmount = evaluationAmount;
            this.fluctuations = fluctuations;
        }
    }

    @Getter
    public static class readStockDetailResponseDto {
        private String stockName;
        private Long evaluation;
        private Long fluctuations;
        private Long myQuantity;
        private List<Long> graphValue;

        @Builder
        public readStockDetailResponseDto(String stockName, Long evaluation, Long fluctuations,
            Long myQuantity, List<Long> graphValue) {
            this.stockName = stockName;
            this.evaluation = evaluation;
            this.fluctuations = fluctuations;
            this.myQuantity = myQuantity;
            this.graphValue = graphValue;
        }
    }


}
