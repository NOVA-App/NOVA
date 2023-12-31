package com.sehbeomschool.nova.domain.stock.service;

import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.stock.dao.StockRepository;
import com.sehbeomschool.nova.domain.stock.dao.StocksInfoRepository;
import com.sehbeomschool.nova.domain.stock.domain.MyStocks;
import com.sehbeomschool.nova.domain.stock.domain.Stock;
import com.sehbeomschool.nova.domain.stock.domain.StocksInfo;
import com.sehbeomschool.nova.global.util.RandomCalculator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockManagerServiceImpl implements StockManagerService {

    private final StockRepository stockRepository;
    private final StocksInfoRepository stocksInfoRepository;

    @Override
    @Transactional
    public void createStocksInfoByGameStart(Ages age) {
        List<Stock> list = stockRepository.findAll();

        for (Stock s : list) {
            Long currentPrice = RandomCalculator.calStock(s.getStartPrice());

            StocksInfo si = StocksInfo.builder()
                .id(null)
                .age(age)
                .stock(s)
                .prevPrice(s.getStartPrice())
                .currentPrice(currentPrice)
                .nextPrice(RandomCalculator.calStock(currentPrice))
                .build();

            stocksInfoRepository.save(si);
        }
    }

    @Override
    @Transactional
    public void createStocksInfoByNextYear(Ages prevAge, Ages nextAge) {
        List<StocksInfo> list = stocksInfoRepository.findStocksInfosByAgeId(prevAge.getId());

        for (StocksInfo si : list) {
            StocksInfo newStocksInfo = StocksInfo.builder()
                .id(null)
                .age(nextAge)
                .stock(si.getStock())
                .prevPrice(si.getCurrentPrice())
                .currentPrice(si.getNextPrice())
                .nextPrice(RandomCalculator.calStock(si.getNextPrice()))
                .build();

            stocksInfoRepository.save(newStocksInfo);
        }
    }

    @Override
    @Transactional
    public void deleteStocksInfo(List<Ages> ages) {
        stocksInfoRepository.deleteStocksInfosByGameIdInQuery(ages);
    }

    @Override
    public Long calStockAssetForNextYear(Game game) {
        Long stockAsset = 0L;

        for (MyStocks ms : game.getMyStocks()) {
            StocksInfo si = stocksInfoRepository.findStocksInfoByAgeIdAndStockId(
                game.getAges().get(game.getAges().size() - 1).getId(), ms.getStock().getId());

            stockAsset += si.getCurrentPrice() * ms.getQuantity();
        }
        
        return stockAsset;
    }
}
