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

    private Long livingCost;

    private Long monthlyRentCost;

    private Long IRPCost;

    private Long childCost;

    private Long loansCost;

    private Long installmentSavingCost;

    @Builder
    public AnnualCost(Long id, Long livingCost, Long monthlyRentCost, Long IRPCost,
        Long childCost, Long loansCost, Long installmentSavingCost) {
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
            .livingCost(FixedValues.LIVING_COST_MIN.getValue().longValue())
            .monthlyRentCost(FixedValues.MONTHLY_RENT_COST.getValue().longValue())
            .IRPCost(0L)
            .childCost(0L)
            .loansCost(0L)
            .installmentSavingCost(0L)
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

    public Long sumOfFixedCost() {
        return sumOfAnnualCost() - this.livingCost;
    }

    public void addChildCost() {
        this.childCost += FixedValues.CHILD_COST.getValue().intValue();
    }
}
