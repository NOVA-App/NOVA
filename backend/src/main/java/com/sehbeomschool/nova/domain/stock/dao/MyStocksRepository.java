package com.sehbeomschool.nova.domain.stock.dao;

import com.sehbeomschool.nova.domain.stock.domain.MyStocks;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyStocksRepository extends JpaRepository<MyStocks, Long> {

    @Query("SELECT ms.quantity FROM MyStocks ms WHERE ms.game.id = :gameId AND ms.stock.id = :stockId")
    Long findQuantityByGameIdAndStockId(@Param("gameId") Long gameId, @Param("stockId") Long stockId);
}
