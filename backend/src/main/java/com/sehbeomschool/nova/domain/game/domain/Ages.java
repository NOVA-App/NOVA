package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.global.constant.FixedValues;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Ages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AGE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    private Long totalAsset;

    private Integer age;

    @Builder
    public Ages(Long id, Game game, Long totalAsset, Integer age) {
        this.id = id;
        this.game = game;
        this.totalAsset = totalAsset;
        this.age = age;
    }

    public static Ages createStartAge(Integer startSalary) {
        return Ages.builder()
            .totalAsset(Long.valueOf(startSalary))
            .age(FixedValues.START_AGE.getValue().intValue())
            .build();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setTotalAsset(Long totalAsset) {
        this.totalAsset = totalAsset;
    }
}
