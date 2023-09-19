package com.sehbeomschool.nova.domain.news.domain;

import com.sehbeomschool.nova.domain.stock.domain.Stock;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("STOCK")
public class StockNews extends News {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NEWS_ID")
    private Stock stock;

    @Builder
    public StockNews(Stock stock) {
        this.stock = stock;
    }
}
