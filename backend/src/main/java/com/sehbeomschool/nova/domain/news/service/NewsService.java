package com.sehbeomschool.nova.domain.news.service;

import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.news.dto.NewsResponseDto.ReadNewsResponseDto;

public interface NewsService {

    ReadNewsResponseDto readNews(Long gameId);

    void createNewsInfoByGameStart(Game game, Ages age);

    void updateNewsInfoByNextYear(Game game, Ages age);
}
