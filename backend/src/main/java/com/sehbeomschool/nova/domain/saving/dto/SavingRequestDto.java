package com.sehbeomschool.nova.domain.saving.dto;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.saving.domain.InsInterest;
import com.sehbeomschool.nova.domain.saving.domain.InstallmentSavings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SavingRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddInstallmentRequestDto {

        private Long gameId;
        private String name;
        private int period;
        private Long amount;

        public InstallmentSavings toEntity(Game game, InsInterest insInterest) {
            return InstallmentSavings.builder().game(game).name(name).amount(amount)
                .totalAmount(amount)
                .interest(insInterest).startAge(game.getCurrentAge())
                .endAge(game.getCurrentAge() + period).build();
        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateIrpRequestDto {

        private Long gameId;
        private Long irpCost;

    }
}
