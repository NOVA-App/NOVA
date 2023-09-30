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
public class AnnualAsset extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNUAL_ASSET_ID")
    private Long id;

    private Long totalAnnualAsset;

    private Long usableAsset;

    private Long livingCost;

    private Long monthlyRentCost;

    private Long IRPCost;

    private Long childCost;

    private Long loansCost;

    private Long installmentSavingCost;

    @Builder
    public AnnualAsset(Long id, Long totalAnnualAsset, Long usableAsset, Long livingCost,
        Long monthlyRentCost, Long IRPCost, Long childCost, Long loansCost,
        Long installmentSavingCost) {
        this.id = id;
        this.totalAnnualAsset = totalAnnualAsset;
        this.usableAsset = usableAsset;
        this.livingCost = livingCost;
        this.monthlyRentCost = monthlyRentCost;
        this.IRPCost = IRPCost;
        this.childCost = childCost;
        this.loansCost = loansCost;
        this.installmentSavingCost = installmentSavingCost;
    }

    public static AnnualAsset createStartAnnualAsset(Integer startSalary) {
        Long startUsableAsset =
            startSalary.longValue() - (FixedValues.LIVING_COST_MIN.getValue().longValue() +
                FixedValues.MONTHLY_RENT_COST.getValue().longValue());

        return AnnualAsset.builder()
            .totalAnnualAsset(startSalary.longValue())
            .usableAsset(startUsableAsset)
            .livingCost(FixedValues.LIVING_COST_MIN.getValue().longValue())
            .monthlyRentCost(FixedValues.MONTHLY_RENT_COST.getValue().longValue())
            .IRPCost(0L)
            .childCost(0L)
            .loansCost(0L)
            .installmentSavingCost(0L)
            .build();
    }

    public Long sumOfFixedCost() {
        Long fixedCost = 0L;
        fixedCost += this.monthlyRentCost;
        fixedCost += this.IRPCost;
        fixedCost += this.childCost;
        fixedCost += this.loansCost;
        fixedCost += this.installmentSavingCost;

        return fixedCost;
    }

    public void updateLivingCost(Long livingCost) {
        this.livingCost = livingCost;
        recalculateUsableAsset();
    }

    public void addChildCost() {
        this.childCost += FixedValues.CHILD_COST.getValue().intValue();
        recalculateUsableAsset();
    }

    private void recalculateUsableAsset() {
        this.usableAsset = this.totalAnnualAsset - (this.livingCost + sumOfFixedCost());
    }

    public void useUsableAsset(Long cost) {
        this.usableAsset -= cost;
        this.totalAnnualAsset -= cost;
    }

    public void earnAsset(Long asset) {
        this.totalAnnualAsset += asset;
        recalculateUsableAsset();
    }

    public void payLivingAndFixedCost() {
        this.totalAnnualAsset -= this.livingCost;
        this.totalAnnualAsset -= sumOfFixedCost();
    }
}
