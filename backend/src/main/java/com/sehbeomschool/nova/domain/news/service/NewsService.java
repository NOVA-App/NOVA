package com.sehbeomschool.nova.domain.news.service;

import com.sehbeomschool.nova.domain.news.domain.News;
import java.util.List;

public interface NewsService {
    List<News> readNews(Long gameId);
}
