package com.sehbeomschool.nova.domain.realty.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class RealtyResponseDto {

    @Getter
    public static class ReadMyRealtyResponseDto {

        private Long investAmounts;
        private Long evaluationAmounts;
        private Long totalRentalIncome;
        private List<MyRealtyResponseDto> myRealties;

        @Builder
        public ReadMyRealtyResponseDto(Long investAmounts, Long evaluationAmounts,
            Long totalRentalIncome,
            List<MyRealtyResponseDto> myRealties) {
            this.investAmounts = investAmounts;
            this.evaluationAmounts = evaluationAmounts;
            this.totalRentalIncome = totalRentalIncome;
            this.myRealties = myRealties;
        }
    }

    @Getter
    public static class MyRealtyResponseDto {

        private Long realtyId;
        private String realtyName;
        private String realtyImg;
        private Long investAmount;
        private Long evaluationAmount;
        private Long reantalIncome;

        @Builder
        public MyRealtyResponseDto(Long realtyId, String realtyName, String realtyImg,
            Long investAmount,
            Long evaluationAmount, Long reantalIncome) {
            this.realtyId = realtyId;
            this.realtyName = realtyName;
            this.realtyImg = realtyImg;
            this.investAmount = investAmount;
            this.evaluationAmount = evaluationAmount;
            this.reantalIncome = reantalIncome;
        }
    }

    @Getter
    public static class ReadMyRealtyDetailResponseDto {

        private Long realtyId;
        private String realtyName;
        private String realtyImg;
        private Long investAmount;
        private Long evaluationAmount;
        private Long depreciationPercent;
        private Long rentIncome;
        private Long principal;

        @Builder
        public ReadMyRealtyDetailResponseDto(Long realtyId, String realtyName, String realtyImg,
            Long investAmount, Long evaluationAmount, Long depreciationPercent, Long rentIncome,
            Long principal) {
            this.realtyId = realtyId;
            this.realtyName = realtyName;
            this.realtyImg = realtyImg;
            this.investAmount = investAmount;
            this.evaluationAmount = evaluationAmount;
            this.depreciationPercent = depreciationPercent;
            this.rentIncome = rentIncome;
            this.principal = principal;
        }
    }

}
