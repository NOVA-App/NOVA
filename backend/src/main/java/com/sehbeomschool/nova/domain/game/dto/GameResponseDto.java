package com.sehbeomschool.nova.domain.game.dto;

import com.sehbeomschool.nova.domain.game.constant.EventType;
import com.sehbeomschool.nova.domain.game.constant.Gender;
import com.sehbeomschool.nova.domain.game.domain.AnnualCost;
import com.sehbeomschool.nova.domain.game.domain.Event;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import java.util.List;
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

    @Getter
    public static class CurrentYearResponseDto {

        private Long gameId;
        private Gender gender;
        private Integer currentAge;
        private Boolean isMarried;
        private Integer numOfChild;
        private UpdateLivingCostResponseDto annualAssets;
        private MyAssetsResponseDto myAssets;

        @Builder
        public CurrentYearResponseDto(Game game, List<Event> events, MyAssets myAssets,
            AnnualCost annualCost) {
            this.gameId = game.getId();
            this.gender = game.getGender();
            this.currentAge = game.getCurrentAge();
            setEventInfo(events);
            this.annualAssets = UpdateLivingCostResponseDto.builder()
                .myAssets(myAssets)
                .annualCost(annualCost)
                .build();
            this.myAssets = MyAssetsResponseDto.builder()
                .myAssets(myAssets)
                .build();
        }

        private void setEventInfo(List<Event> events) {
            this.isMarried = false;
            this.numOfChild = 0;

            for (Event e : events) {
                if (e.getEventType() == EventType.MARRIAGE) {
                    this.isMarried = true;
                    continue;
                }

                this.numOfChild += 1;
            }
        }
    }

    @Getter
    public static class MyAssetsResponseDto {

        private Long totalAsset;
        private Long stocksAsset;
        private Long realtyAsset;
        private Long savingAsset;
        private Long loanAsset;

        @Builder
        public MyAssetsResponseDto(MyAssets myAssets) {
            this.totalAsset = myAssets.getTotalAsset();
            this.stocksAsset = myAssets.getStockAsset();
            this.realtyAsset = myAssets.getRealtyAsset();
            this.savingAsset = myAssets.getIRPAsset() + myAssets.getInstallmentSavingAsset();
            this.loanAsset = myAssets.getLoanAsset();
        }
    }
}
