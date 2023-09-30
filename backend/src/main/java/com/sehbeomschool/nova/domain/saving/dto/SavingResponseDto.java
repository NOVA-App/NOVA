package com.sehbeomschool.nova.domain.saving.dto;

import com.sehbeomschool.nova.domain.saving.domain.InstallmentSavings;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class SavingResponseDto {

    @Getter
    public static class SavingInfoResponseDto {

        private List<InstallmentSavingsDto> installmentSavings;
        private Long irpCost;

        @Builder
        public SavingInfoResponseDto(List<InstallmentSavingsDto> installmentSavings, Long IRPCost) {
            this.installmentSavings = installmentSavings;
            this.irpCost = IRPCost;
        }
    }

    @Getter
    public static class InstallmentSavingsDto {

        private Long id;
        private String name;
        private Long totalAmount;
        private Long amount;
        private int startAge;
        private int endAge;

        @Builder
        public InstallmentSavingsDto(InstallmentSavings installmentSavings) {
            this.id = installmentSavings.getId();
            this.name = installmentSavings.getName();
            this.totalAmount = installmentSavings.getTotalAmount();
            this.amount = installmentSavings.getAmount();
            this.startAge = installmentSavings.getStartAge();
            this.endAge = installmentSavings.getEndAge();
        }
    }


}
