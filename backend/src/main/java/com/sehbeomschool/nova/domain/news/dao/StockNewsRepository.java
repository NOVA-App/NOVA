package com.sehbeomschool.nova.domain.news.dao;

import com.sehbeomschool.nova.domain.news.domain.StockNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockNewsRepository extends JpaRepository<StockNews, Long> {

    @Query(value = "SELECT * FROM stock_news sn, news n "
        + "WHERE sn.news_id = n.news_id AND "
        + "sn.stock_id = :stockId AND "
        + "n.prediction = :prediction "
        + "ORDER BY RAND() LIMIT 1", nativeQuery = true)
    StockNews findStockNewsByRandom(@Param("stockId") Long stockId,
        @Param("prediction") String prediction);
}
