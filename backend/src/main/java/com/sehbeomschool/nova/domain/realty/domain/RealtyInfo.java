package com.sehbeomschool.nova.domain.realty.domain;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RealtyInfo extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "REALTY_INFO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REALTY_ID")
    private Realty realty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    private Long currentPrice;

    private Long prevPrice;

    private Long nextPrice;

    private Long predictedRentIncome;

    @Builder
    public RealtyInfo(Long id, Realty realty, Game game, Long currentPrice, Long prevPrice,
        Long nextPrice, Long predictedRentIncome) {
        this.id = id;
        this.realty = realty;
        this.game = game;
        this.currentPrice = currentPrice;
        this.prevPrice = prevPrice;
        this.nextPrice = nextPrice;
        this.predictedRentIncome = predictedRentIncome;
    }

    public Long calDepreciationPercent() {
        return (this.currentPrice - this.prevPrice) * 100 / this.prevPrice ;
    }

    public Long calEnableLoanAmount(Long myCount) {
        return this.currentPrice / 100 * ((myCount == 0) ? 70L : 40L);
    }

    public void setNextYearPrice(Long nextPrice) {
        this.prevPrice = this.currentPrice;
        this.currentPrice = this.nextPrice;
        this.nextPrice = nextPrice;
        this.predictedRentIncome = this.currentPrice / 20;
    }
}
