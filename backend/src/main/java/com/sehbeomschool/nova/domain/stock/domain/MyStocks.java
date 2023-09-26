package com.sehbeomschool.nova.domain.stock.domain;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class MyStocks extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MY_STOCKS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    private Long investAmount;

    @Builder
    public MyStocks(Long id, Game game, Long quantity, Stock stock, Long investAmount) {
        this.id = id;
        this.game = game;
        this.quantity = quantity;
        this.stock = stock;
        this.investAmount = investAmount;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void updateQuantityAndInvestAmountByBuy(Long purchaseAmount, Long price) {
        this.quantity += purchaseAmount;
        this.investAmount += purchaseAmount * price;
    }

    public void updateQuantityAndInvestAmountBySell(Long purchaseAmount) {
        this.investAmount -= (this.investAmount / this.quantity) * purchaseAmount;
        this.quantity -= purchaseAmount;
    }
}
