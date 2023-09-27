package com.sehbeomschool.nova.domain.news.dao;

import com.sehbeomschool.nova.domain.news.domain.StockNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockNewsRepository extends JpaRepository<StockNews, Long> {

    @Query(value = "SELECT * FROM STOCK_NEWS sn, NEWS n "
        + "WHERE sn.NEWS_ID = n.NEWS_ID AND "
        + "sn.STOCK_ID = :stockId AND "
        + "n.PREDICTION = :prediction "
        + "ORDER BY RAND() LIMIT 1", nativeQuery = true)
    StockNews findStockNewsByRandom(@Param("stockId") Long stockId,
        @Param("prediction") String prediction);
}
