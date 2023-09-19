package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.global.constant.FixedValues;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnualCost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNUAL_COST_ID")
    private Long id;

    private Integer livingCost;

    private Integer monthlyRentCost;

    private Integer IRPCost;

    private Integer childCost;

    private Integer loansCost;

    private Integer installmentSavingCost;

    @Builder
    public AnnualCost(Long id, Integer livingCost, Integer monthlyRentCost, Integer IRPCost,
        Integer childCost, Integer loansCost, Integer installmentSavingCost) {
        this.id = id;
        this.livingCost = livingCost;
        this.monthlyRentCost = monthlyRentCost;
        this.IRPCost = IRPCost;
        this.childCost = childCost;
        this.loansCost = loansCost;
        this.installmentSavingCost = installmentSavingCost;
    }

    public static AnnualCost createStartAnnualCost() {
        return AnnualCost.builder()
            .livingCost(FixedValues.LIVING_COST_MIN.getValue().intValue())
            .monthlyRentCost(FixedValues.MONTHLY_RENT_COST.getValue().intValue())
            .IRPCost(0)
            .childCost(0)
            .loansCost(0)
            .installmentSavingCost(0)
            .build();
    }

    public Long sumOfAnnualCost() {
        Long sum = 0L;
        sum += this.livingCost;
        sum += this.monthlyRentCost;
        sum += this.IRPCost;
        sum += this.installmentSavingCost;
        sum += this.loansCost;
        sum += this.childCost;

        return sum;
    }
}
