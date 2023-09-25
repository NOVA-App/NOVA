package com.sehbeomschool.nova.domain.news.service;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.news.domain.News;
import com.sehbeomschool.nova.domain.news.dto.NewsResponseDto.ReadNewsResponseDto;
import java.util.List;

public interface NewsService {
    ReadNewsResponseDto readNews(Long gameId);

    void createNewsInfoByGameStart(Game game, Long ageId);

    void updateNewsInfoByNextYear(Long gameId, Long ageId);
}
