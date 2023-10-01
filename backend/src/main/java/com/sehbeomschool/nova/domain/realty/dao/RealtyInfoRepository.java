package com.sehbeomschool.nova.domain.realty.dao;

import com.sehbeomschool.nova.domain.realty.domain.RealtyInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RealtyInfoRepository extends JpaRepository<RealtyInfo, Long> {

    List<RealtyInfo> findRealtyInfoByGameId(Long gameId);

    RealtyInfo findRealtyInfoByGameIdAndRealtyId(Long gameId, Long realtyId);

    @Query("SELECT ri FROM RealtyInfo ri WHERE ri.game.id = :gameId AND NOT EXISTS (SELECT 1 FROM MyRealty mr WHERE mr.realty.id = ri.realty.id AND mr.game.id = ri.game.id)")
    List<RealtyInfo> findRealtyInfosByGameIdAndNotinMyRealty(@Param("gameId") Long gameId);

    @Query(value = "SELECT * FROM realty_info WHERE game_id = :gameId ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<RealtyInfo> findRealtyInfosByRandom(@Param("gameId") Long gameId);

    @Transactional
    @Modifying
    @Query("DELETE FROM RealtyInfo ri WHERE ri.game.id = :gameId")
    void deleteRealtyInfoByGameIdInQuery(@Param("gameId") Long gameId);
}
