package com.sehbeomschool.nova.domain.news.service;

import com.sehbeomschool.nova.domain.news.dao.NewsInfoRepository;
import com.sehbeomschool.nova.domain.news.dao.NewsRepository;
import com.sehbeomschool.nova.domain.news.domain.News;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    private final NewsInfoRepository newsInfoRepository;


    public List<News> readNews(Long gameId) {
        return newsInfoRepository.findByGameId(gameId);
    }
}
