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
}
