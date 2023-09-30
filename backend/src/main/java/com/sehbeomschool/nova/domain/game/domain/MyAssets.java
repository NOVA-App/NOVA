package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.domain.game.constant.AssetType;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANNUAL_ASSET_ID")
    private AnnualAsset annualAsset;

    @Column(name = "IPR_ASSET")
    private Long IRPAsset;

    private Long installmentSavingAsset;

    private Long stockAsset;

    private Long realtyAsset;

    private Long loanAsset;

    private Long totalTax;

    @Builder
    public MyAssets(Long id, Long totalAsset, AnnualAsset annualAsset, Long IRPAsset,
        Long installmentSavingAsset, Long stockAsset, Long realtyAsset, Long loanAsset,
        Long totalTax) {
        this.id = id;
        this.totalAsset = totalAsset;
        this.annualAsset = annualAsset;
        this.IRPAsset = IRPAsset;
        this.installmentSavingAsset = installmentSavingAsset;
        this.stockAsset = stockAsset;
        this.realtyAsset = realtyAsset;
        this.loanAsset = loanAsset;
        this.totalTax = totalTax;
    }

    public static MyAssets createStartMyAsset(Integer startSalary, AnnualAsset annualAsset) {
        return MyAssets.builder()
            .totalAsset(startSalary.longValue())
            .annualAsset(annualAsset)
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
        newTotalAsset += this.annualAsset.getTotalAnnualAsset();
        newTotalAsset += this.IRPAsset;
        newTotalAsset += this.installmentSavingAsset;
        newTotalAsset += this.stockAsset;
        newTotalAsset += this.realtyAsset;
        newTotalAsset -= this.loanAsset;

        this.totalAsset = newTotalAsset;
    }

    public void increaseAsset(AssetType assetType, Long value) {
        switch (assetType) {
            case IRP:
                this.IRPAsset += value;
                break;

            case INSTALLMENT_SAVING:
                this.installmentSavingAsset += value;
                break;

            case STOCK:
                this.stockAsset += value;
                break;

            case REALTY:
                this.realtyAsset += value;
                break;

            case LOAN:
                this.loanAsset += value;
                break;

            case TAX:
                this.totalTax += value;
                break;
        }

        recalculateTotalAsset();
    }

    public void decreaseAsset(AssetType assetType, Long value) {
        switch (assetType) {
            case IRP:
                this.IRPAsset -= value;
                break;

            case INSTALLMENT_SAVING:
                this.installmentSavingAsset -= value;
                break;

            case STOCK:
                this.stockAsset -= value;
                break;

            case REALTY:
                this.realtyAsset -= value;
                break;

            case LOAN:
                this.loanAsset -= value;
                break;
        }

        recalculateTotalAsset();
    }
}
