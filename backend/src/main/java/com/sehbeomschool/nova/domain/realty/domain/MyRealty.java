package com.sehbeomschool.nova.domain.realty.domain;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyRealty extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MY_REALTY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REALTY_ID")
    private Realty realty;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "LOAN_ID")
    private Loan loan;

    private Long investAmount;

    private Long rentIncome;

    @Builder
    public MyRealty(Long id, Game game, Realty realty, Loan loan, Long investAmount,
        Long rentIncome) {
        this.id = id;
        this.game = game;
        this.realty = realty;
        this.loan = loan;
        this.investAmount = investAmount;
        this.rentIncome = rentIncome;
    }
}
