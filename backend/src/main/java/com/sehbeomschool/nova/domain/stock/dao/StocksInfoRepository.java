package com.sehbeomschool.nova.domain.stock.dao;

import com.sehbeomschool.nova.domain.stock.domain.StocksInfo;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StocksInfoRepository extends JpaRepository<StocksInfo, Long> {

    @Query("SELECT si.currentPrice FROM StocksInfo si WHERE si.id IN (SELECT a.id FROM Ages a WHERE a.game.id = :gameId) AND si.stock.id = :stockId")
    List<Long> findCurrentPricesByGameIdAndStockId(@Param("gameId") Long gameId,
        @Param("stockId") Long stockId);

    @Query("SELECT si FROM StocksInfo si WHERE si.age.id = (SELECT a.id FROM Ages a WHERE a.game.id = :gameId AND a.age =(SELECT g.currentAge FROM Game g WHERE g.id = :gameId))")
    List<StocksInfo> findStocksInfoListByGameId(@Param("gameId") Long gameId);

    @Query("SELECT si FROM StocksInfo si WHERE si.age.id = (SELECT a.id FROM Ages a WHERE a.game.id = :gameId AND a.age =(SELECT g.currentAge FROM Game g WHERE g.id = :gameId)) AND si.stock.id = :stockId")
    StocksInfo findStocksInfoByGameIdAndStockId(@Param("gameId") Long gameId, @Param("stockId") Long stockId);
}
