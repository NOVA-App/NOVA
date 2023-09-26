package com.sehbeomschool.nova.domain.news.dao;

import com.sehbeomschool.nova.domain.news.domain.NewsInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsInfoRepository extends JpaRepository<NewsInfo, Long> {

    @Query("SELECT ni.news.content FROM NewsInfo ni WHERE ni.game.id = :gameId")
    List<String> findContentByGameId(@Param("gameId") Long gameId);

    List<NewsInfo> findNewsInfosByGameId(Long gameId);
}
