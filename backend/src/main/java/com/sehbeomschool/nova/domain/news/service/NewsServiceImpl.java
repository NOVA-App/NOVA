package com.sehbeomschool.nova.domain.news.service;

import static com.sehbeomschool.nova.domain.news.domain.Prediction.BAD;
import static com.sehbeomschool.nova.domain.news.domain.Prediction.GOOD;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.news.dao.NewsInfoRepository;
import com.sehbeomschool.nova.domain.news.dao.NewsRepository;
import com.sehbeomschool.nova.domain.news.dao.RealtyNewsRepository;
import com.sehbeomschool.nova.domain.news.dao.StockNewsRepository;
import com.sehbeomschool.nova.domain.news.domain.News;
import com.sehbeomschool.nova.domain.news.domain.NewsInfo;
import com.sehbeomschool.nova.domain.news.domain.Prediction;
import com.sehbeomschool.nova.domain.news.dto.NewsResponseDto.ReadNewsResponseDto;
import com.sehbeomschool.nova.domain.realty.dao.RealtyInfoRepository;
import com.sehbeomschool.nova.domain.realty.domain.RealtyInfo;
import com.sehbeomschool.nova.domain.stock.dao.StocksInfoRepository;
import com.sehbeomschool.nova.domain.stock.domain.StocksInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsInfoRepository newsInfoRepository;
    private final RealtyNewsRepository realtyNewsRepository;
    private final StockNewsRepository stockNewsRepository;
    private final RealtyInfoRepository realtyInfoRepository;
    private final StocksInfoRepository stocksInfoRepository;


    public ReadNewsResponseDto readNews(Long gameId) {
        ReadNewsResponseDto dto = ReadNewsResponseDto.builder()
            .news(newsInfoRepository.findContentByGameId(gameId))
            .build();

        return dto;
    }

    @Override
    @Transactional
    public void createNewsInfoByGameStart(Game game, Long ageId) {
        List<StocksInfo> stocksInfos = stocksInfoRepository.findStocksInfosByRandom(ageId);
        List<RealtyInfo> realtyInfos = realtyInfoRepository.findRealtyInfosByRandom(game.getId());

        List<News> list = new ArrayList<>();

        for (StocksInfo si : stocksInfos) {
            Prediction prediction =
                (si.getNextPrice() - si.getCurrentPrice()) > 0 ? GOOD : BAD;
            list.add(stockNewsRepository.findStockNewsByRandom(si.getStock().getId(), prediction));
        }

        for (RealtyInfo ri : realtyInfos) {
            Prediction prediction =
                (ri.getNextPrice() - ri.getCurrentPrice()) > 0 ? GOOD : BAD;
            list.add(realtyNewsRepository.findRealtyNewsByRandom(ri.getRealty().getId(),
                prediction));
        }

        Collections.shuffle(list);

        for (News n : list) {
            NewsInfo newsInfo = NewsInfo.builder()
                .id(null)
                .game(game)
                .news(n)
                .build();

            newsInfoRepository.save(newsInfo);
        }
    }

    @Override
    @Transactional
    public void updateNewsInfoByNextYear(Long gameId, Long ageId) {
        List<NewsInfo> list = newsInfoRepository.findNewsInfosByGameId(gameId);

        List<StocksInfo> stocksInfos = stocksInfoRepository.findStocksInfosByRandom(ageId);
        List<RealtyInfo> realtyInfos = realtyInfoRepository.findRealtyInfosByRandom(gameId);

        List<News> news = new ArrayList<>();

        for (StocksInfo si : stocksInfos) {
            Prediction prediction =
                (si.getNextPrice() - si.getCurrentPrice()) > 0 ? GOOD : BAD;
            news.add(stockNewsRepository.findStockNewsByRandom(si.getStock().getId(), prediction));
        }

        for (RealtyInfo ri : realtyInfos) {
            Prediction prediction =
                (ri.getNextPrice() - ri.getCurrentPrice()) > 0 ? GOOD : BAD;
            news.add(realtyNewsRepository.findRealtyNewsByRandom(ri.getRealty().getId(),
                prediction));
        }

        Collections.shuffle(news);

        for (int i = 0; i < list.size(); i++) {
            NewsInfo ni = list.get(i);
            ni.setNews(news.get(i));
        }
    }


}
