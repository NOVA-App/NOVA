package com.sehbeomschool.nova.domain.stock.domain;

import com.sehbeomschool.nova.domain.game.domain.Game;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class MyStocks {

    @Id
    @GeneratedValue
    @Column(name = "MY_STOCKS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    private Long quantity;

    @ManyToOne
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
}
