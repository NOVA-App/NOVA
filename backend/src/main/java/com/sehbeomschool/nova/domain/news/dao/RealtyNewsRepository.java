package com.sehbeomschool.nova.domain.news.dao;

import com.sehbeomschool.nova.domain.news.domain.RealtyNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RealtyNewsRepository extends JpaRepository<RealtyNews, Long> {

    @Query(value = "SELECT * FROM realty_news rn, news n "
        + "WHERE rn.news_id = n.news_id AND "
        + "rn.realty_id = :realtyId AND "
        + "n.prediction = :prediction "
        + "ORDER BY RAND() LIMIT 1", nativeQuery = true)
    RealtyNews findRealtyNewsByRandom(@Param("realtyId") Long realtyId,
        @Param("prediction") String prediction);
}
