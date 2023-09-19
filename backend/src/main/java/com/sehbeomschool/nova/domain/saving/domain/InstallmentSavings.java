package com.sehbeomschool.nova.domain.saving.domain;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class InstallmentSavings extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTALLMENT_SAVING_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @OneToOne
    @JoinColumn(name = "INS_INTEREST_ID")
    private InsInterest interest;

    private String name;
    private Long amount;
    private Long totalAmount;
    private int startAge;
    private int endAge;

    @Builder
    public InstallmentSavings(Long id, Game game, InsInterest interest, String name, Long amount,
        Long totalAmount, int startAge, int endAge) {
        this.id = id;
        this.game = game;
        this.interest = interest;
        this.name = name;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.startAge = startAge;
        this.endAge = endAge;
    }


}
