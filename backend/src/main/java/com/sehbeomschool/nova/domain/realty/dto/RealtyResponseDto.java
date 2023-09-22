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

    @Getter
    public static class ReadRealtyResponseDto {
        private Long realtyId;
        private String realtyName;
        private String realtyImg;
        private String region;
        private Long depreciationPercent;
        private Long evaluationAmount;
        private Long predictedRentIncome;

        @Builder
        public ReadRealtyResponseDto(Long realtyId, String realtyName, String realtyImg,
            String region,
            Long depreciationPercent, Long evaluationAmount, Long predictedRentIncome) {
            this.realtyId = realtyId;
            this.realtyName = realtyName;
            this.realtyImg = realtyImg;
            this.region = region;
            this.depreciationPercent = depreciationPercent;
            this.evaluationAmount = evaluationAmount;
            this.predictedRentIncome = predictedRentIncome;
        }
    }

    @Getter
    public static class ReadRealtyDetailResponseDto {
        private Long realtyId;
        private String realtyName;
        private String realtyImg;
        private Long depreciationPercent;
        private String region;
        private Long predictedRentIncome;
        private Long totalPrice;
        private Long evaluationAmount;
        private Long acquistionTax;
        private Long enableLoanAmount;

        @Builder
        public ReadRealtyDetailResponseDto(Long realtyId, String realtyName, String realtyImg,
            Long depreciationPercent, String region, Long predictedRentIncome, Long totalPrice,
            Long evaluationAmount, Long acquistionTax, Long enableLoanAmount) {
            this.realtyId = realtyId;
            this.realtyName = realtyName;
            this.realtyImg = realtyImg;
            this.depreciationPercent = depreciationPercent;
            this.region = region;
            this.predictedRentIncome = predictedRentIncome;
            this.totalPrice = totalPrice;
            this.evaluationAmount = evaluationAmount;
            this.acquistionTax = acquistionTax;
            this.enableLoanAmount = enableLoanAmount;
        }
    }
}
