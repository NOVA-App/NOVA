package com.sehbeomschool.nova.domain.news.domain;

import com.sehbeomschool.nova.domain.realty.domain.Realty;
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
@DiscriminatorValue("REALTY")
public class RealtyNews extends News {
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "REALTY_ID")
//    // TODO: Realty import 추가
//    private Realty realty;

//    @Builder
//    public RealtyNews(Realty realty) {
//        this.realty = realty;
//    }
}
