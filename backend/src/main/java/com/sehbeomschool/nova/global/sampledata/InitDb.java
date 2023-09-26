package com.sehbeomschool.nova.global.sampledata;

import com.sehbeomschool.nova.domain.news.domain.Prediction;
import com.sehbeomschool.nova.domain.news.domain.RealtyNews;
import com.sehbeomschool.nova.domain.news.domain.StockNews;
import com.sehbeomschool.nova.domain.realty.domain.Realty;
import com.sehbeomschool.nova.domain.stock.domain.Stock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.initDb.enable", havingValue = "true")
public class InitDb {

    private final InitService initService;

    static final String csvSplitBy = ",";

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            makeStockAndNews();
            makeRealtyAndNews();
        }

        public void makeStockAndNews() {
            List<Stock> stockList = new ArrayList<>();
            List<StockNews> stockNewsList = new ArrayList<>();

            try {
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                        new ClassPathResource("data/stock.csv").getInputStream()));
                for (int i = 0; i < 8; i++) {
                    String[] data = br.readLine().split(csvSplitBy);
                    Stock stock = Stock.builder()
                        .name(data[0])
                        .startPrice(Long.parseLong(data[1]))
                        .build();

                    stockList.add(stock);
                }

                br = new BufferedReader(
                    new InputStreamReader(
                        new ClassPathResource("data/stock_news.csv").getInputStream()));

                for (Stock stock : stockList) {
                    for (int i = 0; i < 20; i++) {
                        String[] data = br.readLine().split(csvSplitBy);
                        StockNews sn = StockNews.builder()
                            .stock(stock)
                            .content(data[1])
                            .prediction(Prediction.valueOf(data[2]))
                            .build();

                        stockNewsList.add(sn);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Stock s : stockList) {
                em.persist(s);
            }

            for (StockNews sn : stockNewsList) {
                em.persist(sn);
            }
        }

        public void makeRealtyAndNews() {
            List<Realty> realtyList = new ArrayList<>();
            List<RealtyNews> realtyNewsList = new ArrayList<>();

            try {
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                        new ClassPathResource("data/realty.csv").getInputStream()));
                for (int i = 0; i < 8; i++) {
                    String[] data = br.readLine().split(csvSplitBy);
                    Realty realty = Realty.builder()
                        .name(data[0])
                        .startPrice(Long.valueOf(data[1]))
                        .realtyImg(data[2])
                        .region(data[3])
                        .build();

                    realtyList.add(realty);
                }

                br = new BufferedReader(
                    new InputStreamReader(
                        new ClassPathResource("data/realty_news.csv").getInputStream()));

                for (Realty r : realtyList) {
                    for (int i = 0; i < 20; i++) {
                        String[] data = br.readLine().split(csvSplitBy);
                        RealtyNews rn = RealtyNews.builder()
                            .realty(r)
                            .content(data[1])
                            .prediction(Prediction.valueOf(data[2]))
                            .build();

                        realtyNewsList.add(rn);
                    }
                }

                for (Realty r : realtyList) {
                    em.persist(r);
                }

                for (RealtyNews rn : realtyNewsList) {
                    em.persist(rn);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
