package com.sehbeomschool.nova.domain.game.domain;

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
public class MyAssets extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MY_ASSET_ID")
    private Long id;

    private Long totalAsset;

    private Long usableAsset;

    @Column(name = "IPR_ASSET")
    private Long IRPAsset;

    private Long installmentSavingAsset;

    private Long stockAsset;

    private Long realtyAsset;

    private Long loanAsset;

    private Long totalTax;

    @Builder
    public MyAssets(Long id, Long totalAsset, Long usableAsset, Long IRPAsset,
        Long installmentSavingAsset, Long stockAsset, Long realtyAsset, Long loanAsset,
        Long totalTax) {
        this.id = id;
        this.totalAsset = totalAsset;
        this.usableAsset = usableAsset;
        this.IRPAsset = IRPAsset;
        this.installmentSavingAsset = installmentSavingAsset;
        this.stockAsset = stockAsset;
        this.realtyAsset = realtyAsset;
        this.loanAsset = loanAsset;
        this.totalTax = totalTax;
    }

    public static MyAssets createStartMyAsset(Integer startSalary, AnnualCost annualCost) {
        return MyAssets.builder()
            .totalAsset(Long.valueOf(startSalary))
            .usableAsset(startSalary - annualCost.sumOfAnnualCost())
            .IRPAsset(0L)
            .installmentSavingAsset(0L)
            .stockAsset(0L)
            .realtyAsset(0L)
            .loanAsset(0L)
            .totalTax(0L)
            .build();
    }

    public void recalculateTotalAsset() {
        Long newTotalAsset = 0L;
        newTotalAsset += this.usableAsset;
        newTotalAsset += this.IRPAsset;
        newTotalAsset += this.installmentSavingAsset;
        newTotalAsset += this.stockAsset;
        newTotalAsset += this.realtyAsset;
        newTotalAsset -= this.loanAsset;

        this.totalAsset = newTotalAsset;
    }

    public void useUsableAsset(Long cost) {
        this.usableAsset -= cost;
        recalculateTotalAsset();
    }

    public void setUsableAssetByAnnualCost(Long totalAnnualCost) {
        this.usableAsset = this.totalAsset;

        this.usableAsset -= this.IRPAsset;
        this.usableAsset -= this.installmentSavingAsset;
        this.usableAsset -= this.stockAsset;
        this.usableAsset -= this.realtyAsset;
        this.usableAsset -= totalAnnualCost;
    }
}
