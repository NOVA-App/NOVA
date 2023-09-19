package com.sehbeomschool.nova.domain.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class StocksInfo {

    @Id
    @GeneratedValue
    @Column(name = "STOCKS_INFO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "AGE_ID")
    // TODO: Age 추가 시 import문 최신화 필요  
    private Age age;

    @ManyToOne
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    private Long currentPrice;

    private Long prevPrice;

    private Long nextPrice;

    @Builder
    public StocksInfo(Long id, Age age, Stock stock, Long currentPrice, Long prevPrice,
        Long nextPrice) {
        this.id = id;
        this.age = age;
        this.stock = stock;
        this.currentPrice = currentPrice;
        this.prevPrice = prevPrice;
        this.nextPrice = nextPrice;
    }
}
