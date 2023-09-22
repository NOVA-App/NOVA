package com.sehbeomschool.nova.domain.stock.domain;

import com.sehbeomschool.nova.domain.game.domain.Ages;
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
public class StocksInfo extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "STOCKS_INFO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGE_ID")
    private Ages age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    private Long currentPrice;

    private Long prevPrice;

    private Long nextPrice;

    @Builder
    public StocksInfo(Long id, Ages age, Stock stock, Long currentPrice, Long prevPrice,
        Long nextPrice) {
        this.id = id;
        this.age = age;
        this.stock = stock;
        this.currentPrice = currentPrice;
        this.prevPrice = prevPrice;
        this.nextPrice = nextPrice;
    }

    public Long calFluctuaions(){
        return this.currentPrice - this.prevPrice;
    }
}
