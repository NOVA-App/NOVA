package com.sehbeomschool.nova.domain.news.dao;

import com.sehbeomschool.nova.domain.news.domain.News;
import com.sehbeomschool.nova.domain.news.domain.NewsInfo;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsInfoRepository extends JpaRepository<NewsInfo, Long> {

    @Query("SELECT ni.news FROM NewsInfo ni WHERE ni.game.id = :gameId")
    List<News> findByGameId(@Param("gameId") Long gameId);
}
