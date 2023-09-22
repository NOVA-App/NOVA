package com.sehbeomschool.nova.domain.realty.dao;

import com.sehbeomschool.nova.domain.realty.domain.RealtyInfo;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RealtyInfoRepository extends JpaRepository<RealtyInfo, Long> {

    List<RealtyInfo> findRealtyInfoByGameId(Long gameId);

    RealtyInfo findRealtyInfoByGameIdAndRealtyId(Long gameId, Long realtyId);

    @Query("SELECT ri FROM RealtyInfo ri WHERE ri.game.id = :gameId AND NOT EXISTS (SELECT 1 FROM MyRealty mr WHERE mr.realty.id = ri.realty.id AND mr.game.id = ri.game.id)")
    List<RealtyInfo> findRealtyInfosByGameIdAndNotinMyRealty(@Param("gameId") Long gameId);
}
