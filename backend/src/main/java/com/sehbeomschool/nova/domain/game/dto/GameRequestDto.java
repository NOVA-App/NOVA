package com.sehbeomschool.nova.domain.game.dto;

import com.sehbeomschool.nova.domain.game.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GameRequestDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GameStartRequestDto {

        private Integer startSalary;
        private Gender gender;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MarryRequestDto {

        private Long gameId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateLivingCostRequestDto {

        private Long gameId;
        private Long livingCost;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NextYearRequestDto {

        private Long gameId;
        private Boolean isChildBirth;
    }
}
