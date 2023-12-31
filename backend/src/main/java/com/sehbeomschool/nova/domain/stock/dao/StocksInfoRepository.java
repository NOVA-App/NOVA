package com.sehbeomschool.nova.domain.stock.dao;

import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.domain.stock.domain.StocksInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StocksInfoRepository extends JpaRepository<StocksInfo, Long> {

    List<StocksInfo> findStocksInfosByAgeId(Long ageId);

    @Query("SELECT si.currentPrice FROM StocksInfo si WHERE si.age.id IN (SELECT a.id FROM Ages a WHERE a.game.id = :gameId) AND si.stock.id = :stockId")
    List<Long> findCurrentPricesByGameIdAndStockId(@Param("gameId") Long gameId,
        @Param("stockId") Long stockId);

    @Query("SELECT si FROM StocksInfo si WHERE si.age.id = (SELECT a.id FROM Ages a WHERE a.game.id = :gameId AND a.age =(SELECT g.currentAge FROM Game g WHERE g.id = :gameId))")
    List<StocksInfo> findStocksInfoListByGameId(@Param("gameId") Long gameId);

    @Query("SELECT si FROM StocksInfo si WHERE si.age.id = (SELECT a.id FROM Ages a WHERE a.game.id = :gameId AND a.age =(SELECT g.currentAge FROM Game g WHERE g.id = :gameId)) AND si.stock.id = :stockId")
    StocksInfo findStocksInfoByGameIdAndStockId(@Param("gameId") Long gameId,
        @Param("stockId") Long stockId);

    @Query(value = "SELECT * FROM stocks_info WHERE age_id = :ageId ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<StocksInfo> findStocksInfosByRandom(@Param("ageId") Long ageId);

    StocksInfo findStocksInfoByAgeIdAndStockId(Long ageId, Long stockId);

    @Transactional
    @Modifying
    @Query("DELETE FROM StocksInfo si WHERE si.age in :ages")
    void deleteStocksInfosByGameIdInQuery(@Param("ages") List<Ages> ages);
}
