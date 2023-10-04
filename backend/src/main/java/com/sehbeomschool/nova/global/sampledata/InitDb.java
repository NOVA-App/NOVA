package com.sehbeomschool.nova.global.sampledata;

import com.sehbeomschool.nova.domain.game.domain.AnalysisComment;
import com.sehbeomschool.nova.domain.news.domain.Prediction;
import com.sehbeomschool.nova.domain.news.domain.RealtyNews;
import com.sehbeomschool.nova.domain.news.domain.StockNews;
import com.sehbeomschool.nova.domain.realty.domain.Realty;
import com.sehbeomschool.nova.domain.saving.domain.InsInterest;
import com.sehbeomschool.nova.domain.stock.domain.Stock;
import com.sehbeomschool.nova.domain.user.domain.User;
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
            makeAnalysisComments();
            makeInsInterest();
            makeTestUser();
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

        private void makeAnalysisComments() {
            String[] eatOutComments = new String[]{
                "외식 월 1~2회",
                "외식 월 2~4회",
                "외식 월 4~6회",
                "외식 월 6~8회",
                "외식 월 8회 이상"
            };

            String[] tripComments = new String[]{
                "여행 년 1회",
                "여행 년 2회",
                "여행 년 2~4회",
                "여행 년 4회 이상",
                "여행 년 6회 이상"
            };

            String[] carComments = new String[]{
                "자동차는 추천하지 않아요.",
                "중고 차량을 운영할 수 있어요.",
                "신차 혹은 중고 차량을 운영할 수 있어요.",
                "신차 혹은 중고 차량에 높은 수준의 편의 기능을 추가할 수 있어요.",
                "여러 대의 고급 자동차를 운영할 수 있어요."
            };

            String[] hobbyComments = new String[]{
                "취미는 저렴하고 간단한 독서, 자전거 타기 등을 추천해요.",
                "취미는 요리, 등산 등을 즐길 수 있어요.",
                "취미는 테니스, 음악 등을 즐길 수 있어요.",
                "취미는 골프, 예술 수업 등을 즐길 수 있어요.",
                "취미는 골프, 요리 레슨 등을 즐길 수 있어요."
            };

            String[] generalComments = new String[]{
                "생활 수준을 절약적으로 조절해야 해요!",
                "편안한 노후 생활을 계획할 수 있어요!",
                "편안하고 안정적인 노후 생활을 즐길 수 있어요!",
                "안정적으로 다양한 활동을 즐길 수 있어요!",
                "풍족하고 편안한 노후를 즐길 수 있어요!"
            };

            int[][] assetRange = new int[][]{
                {Integer.MIN_VALUE, 2_000_000},
                {2_000_001, 4_000_000},
                {4_000_001, 6_000_000},
                {6_000_001, 8_000_000},
                {8_000_000, Integer.MAX_VALUE}
            };

            for (int i = 0; i < 5; i++) {
                em.persist(AnalysisComment.builder()
                    .eatOutComment(eatOutComments[i])
                    .tripComment(tripComments[i])
                    .carComment(carComments[i])
                    .hobbyComment(hobbyComments[i])
                    .generalComment(generalComments[i])
                    .minAsset(assetRange[i][0])
                    .maxAsset(assetRange[i][1])
                    .build());
            }
        }

        private void makeInsInterest() {
            em.persist(InsInterest.builder()
                .period(1).interest(5).build());

            em.persist(InsInterest.builder()
                .period(2).interest(7).build());

            em.persist(InsInterest.builder()
                .period(3).interest(10).build());
        }

        private void makeTestUser() {
            em.persist(User.builder().name("testUser").build());
        }
    }


}
