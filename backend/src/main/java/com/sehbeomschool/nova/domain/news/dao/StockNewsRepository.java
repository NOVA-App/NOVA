package com.sehbeomschool.nova.domain.news.dao;

import com.sehbeomschool.nova.domain.news.domain.News;
import com.sehbeomschool.nova.domain.news.domain.StockNews;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockNewsRepository extends JpaRepository<StockNews, Long> {
    @Query(value = "SELECT * FROM STOCK_NEWS sn WHERE sn.STOCK_ID = :stockId AND sn.PREDICTION = :prediction ORDER BY RAND() LIMIT 1", nativeQuery = true)
    StockNews findStockNewsByRandom(@Param("stockId") Long stockId, @Param("prediction") String prediction);
}
