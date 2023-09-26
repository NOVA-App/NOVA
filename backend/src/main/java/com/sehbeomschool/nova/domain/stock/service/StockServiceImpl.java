package com.sehbeomschool.nova.domain.stock.service;

import com.sehbeomschool.nova.domain.stock.dao.MyStocksRepository;
import com.sehbeomschool.nova.domain.stock.dao.StockRepository;
import com.sehbeomschool.nova.domain.stock.dao.StocksInfoRepository;
import com.sehbeomschool.nova.domain.stock.domain.MyStocks;
import com.sehbeomschool.nova.domain.stock.domain.StocksInfo;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.MyStockResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readMyStocksResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStockDetailResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStocksListResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final MyStocksRepository myStocksRepository;
    private final StocksInfoRepository stocksInfoRepository;
    private final StockRepository stockRepository;

    @Override
    public List<readStocksListResponseDto> readStocksList(Long gameId) {
        List<StocksInfo> list = stocksInfoRepository.findStocksInfoListByGameId(gameId);

        List<readStocksListResponseDto> readStocksListResponseDtoList = new ArrayList<>();

        for (StocksInfo si : list) {
            readStocksListResponseDto dto = readStocksListResponseDto.builder()
                .stockId(si.getStock().getId())
                .stockName(si.getStock().getName())
                .evaluationAmount(si.getPrevPrice())
                .fluctuations(si.calFluctuaions())
                .build();

            readStocksListResponseDtoList.add(dto);
        }

        return readStocksListResponseDtoList;
    }

    @Override
    public readStockDetailResponseDto readStockDetail(Long gameId, Long stockId) {
        StocksInfo stocksInfo = stocksInfoRepository.findStocksInfoByGameIdAndStockId(gameId,
            stockId);
        List<Long> priceList = stocksInfoRepository.findCurrentPricesByGameIdAndStockId(gameId,
            stockId);
        Long myQuantity = myStocksRepository.findQuantityByGameIdAndStockId(gameId, stockId);

        if (stocksInfo == null) {
            return null;
        }

        readStockDetailResponseDto dto = readStockDetailResponseDto.builder()
            .stockName(stocksInfo.getStock().getName())
            .evaluation(stocksInfo.getCurrentPrice())
            .fluctuations(stocksInfo.calFluctuaions())
            .myQuantity(myQuantity == null ? 0 : myQuantity)
            .graphValue(priceList)
            .build();
        return dto;
    }

    @Override
    public readMyStocksResponseDto readMyStocks(Long gameId) {
        Long investAmounts = 0L;
        Long evaluationAmounts = 0L;
        Long depreciation = 0L;
        Long depreciationPercent = 0L;

        List<MyStockResponseDto> list = new ArrayList<>();

        List<MyStocks> myStocks = myStocksRepository.findMyStocksByGameId(gameId);

        if (myStocks.size() == 0) {
            return null;
        }

        for (MyStocks myStock : myStocks) {
            StocksInfo myStockInfo = stocksInfoRepository.findStocksInfoByGameIdAndStockId(gameId,
                myStock.getId());

            investAmounts += myStock.getInvestAmount();
            evaluationAmounts += myStockInfo.getCurrentPrice() * myStock.getQuantity();
            Long fluctuation = myStockInfo.getCurrentPrice() - myStockInfo.getPrevPrice();

            MyStockResponseDto myStockResponseDto = MyStockResponseDto.builder()
                .stockId(myStock.getId())
                .stockName(myStock.getStock().getName())
                .evaluationAmount(myStockInfo.getCurrentPrice() * myStock.getQuantity())
                .fluctuations(fluctuation * myStock.getQuantity())
                .fluctuationsPercent(
                    fluctuation * myStock.getQuantity() / myStock.getInvestAmount() * 100)
                .build();

            list.add(myStockResponseDto);

        }

        depreciation = evaluationAmounts - investAmounts;
        depreciationPercent = depreciation / investAmounts * 100;

        readMyStocksResponseDto dto = readMyStocksResponseDto.builder()
            .investAmounts(investAmounts)
            .evaluationAmounts(evaluationAmounts)
            .depreciation(depreciation)
            .depreciationPercent(depreciationPercent)
            .myStocks(list)
            .build();

        return dto;
    }
}
