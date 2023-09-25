package com.sehbeomschool.nova.domain.news.service;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.news.domain.News;
import java.util.List;

public interface NewsService {
    List<String> readNews(Long gameId);

    void createNewsInfoByGameStart(Game game, Long ageId);

    void updateNewsInfoByNextYear(Long gameId, Long ageId);
}
