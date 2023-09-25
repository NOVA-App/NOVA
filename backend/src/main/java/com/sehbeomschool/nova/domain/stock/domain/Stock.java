package com.sehbeomschool.nova.domain.stock.domain;

import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "STOCK_ID")
    private Long id;

    private String name;

    private Long startPrice;

    @Builder
    public Stock(Long id, String name, Long startPrice) {
        this.id = id;
        this.name = name;
        this.startPrice = startPrice;
    }
}
