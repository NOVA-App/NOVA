package com.sehbeomschool.nova.domain.news.domain;

import com.sehbeomschool.nova.domain.stock.domain.Stock;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@DiscriminatorValue("Stock")
public class StockNews extends News{
    @ManyToOne
    @JoinColumn(name = "NEWS_ID")
    private Stock stock;
}
