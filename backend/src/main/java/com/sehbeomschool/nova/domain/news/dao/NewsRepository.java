package com.sehbeomschool.nova.domain.news.dao;

import com.sehbeomschool.nova.domain.news.domain.News;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n JOIN StockNews sn ON n.id = sn.id JOIN Stock s ON sn.id = s.id WHERE s.id = :stockId AND n.prediction = :prediction")
    List<News> findByStockAndPrediction(@Param("stockId") Long stockId, @Param("prediction") String prediction);

    @Query("SELECT n FROM News n JOIN RealtyNews rn ON n.id = rn.id JOIN Realty r ON rn.id = r.id WHERE r.id = :realtyId AND n.prediction = :prediction")
    List<News> findByRealtyAndPrediction(@Param("realtyId") Long realtyId, @Param("prediction") String prediction);
}
