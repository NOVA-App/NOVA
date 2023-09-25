package com.sehbeomschool.nova.domain.news.service;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.news.dto.NewsResponseDto.ReadNewsResponseDto;

public interface NewsService {

    ReadNewsResponseDto readNews(Long gameId);

    void createNewsInfoByGameStart(Game game, Long ageId);

    void updateNewsInfoByNextYear(Long gameId, Long ageId);
}
