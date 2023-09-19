package com.sehbeomschool.nova.domain.news.domain;

import com.sehbeomschool.nova.domain.realty.domain.Realty;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@DiscriminatorValue("REALTY")
public class RealtyNews extends News {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REALTY_ID")
    private Realty realty;
}
