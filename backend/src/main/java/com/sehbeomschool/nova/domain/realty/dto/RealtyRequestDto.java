package com.sehbeomschool.nova.domain.realty.dto;

import lombok.Builder;
import lombok.Getter;

public class RealtyRequestDto {

    @Getter
    public static class TradeRealtyRequestDto {

        private Long gameId;
        private Long realtyId;
        private Long principalAmount;

        @Builder
        public TradeRealtyRequestDto(Long gameId, Long realtyId, Long principalAmount) {
            this.gameId = gameId;
            this.realtyId = realtyId;
            this.principalAmount = principalAmount;
        }
    }

}
