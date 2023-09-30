package com.sehbeomschool.nova.domain.realty.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RealtyRequestDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TradeRealtyRequestDto {

        private Long gameId;
        private Long realtyId;
        private Long principalAmount;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RepaymentLoanRequestDto {

        private Long gameId;
        private Long realtyId;
        private Long principalAmount;
    }
}
