package com.sehbeomschool.nova.domain.game.dto;

import com.sehbeomschool.nova.domain.game.domain.AnnualCost;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import lombok.Builder;
import lombok.Getter;

public class GameResponseDto {

    @Getter
    public static class GameStartResponseDto {

        private Long gameId;

        @Builder
        public GameStartResponseDto(Long gameId) {
            this.gameId = gameId;
        }
    }

    @Getter
    public static class FixedCostResponseDto {

        private Long totalFixedCost;
        private Long monthlyRentCost;
        private Long IRPCost;
        private Long childCost;
        private Long loansCost;
        private Long installmentSavingCost;

        @Builder
        public FixedCostResponseDto(AnnualCost annualCost) {
            this.totalFixedCost = annualCost.sumOfFixedCost();
            this.monthlyRentCost = annualCost.getMonthlyRentCost();
            this.IRPCost = annualCost.getIRPCost();
            this.childCost = annualCost.getChildCost();
            this.loansCost = annualCost.getLoansCost();
            this.installmentSavingCost = annualCost.getInstallmentSavingCost();
        }
    }

    @Getter
    public static class UpdateLivingCostResponseDto {

        private Long usableAsset;
        private Long livingCost;
        private FixedCostResponseDto fixedCost;

        @Builder
        public UpdateLivingCostResponseDto(MyAssets myAssets, AnnualCost annualCost) {
            this.usableAsset = myAssets.getUsableAsset();
            this.livingCost = annualCost.getLivingCost();
            this.fixedCost = FixedCostResponseDto.builder().annualCost(annualCost).build();
        }
    }
}
