package com.sehbeomschool.nova.domain.realty.domain;

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
public class Realty extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "REALTY_ID")
    private Long id;

    private Long startPrice;

    private String name;

    private String realtyImg;

    private String region;

    @Builder
    public Realty(Long id, Long startPrice, String name, String realtyImg, String region) {
        this.id = id;
        this.startPrice = startPrice;
        this.name = name;
        this.realtyImg = realtyImg;
        this.region = region;
    }
}
