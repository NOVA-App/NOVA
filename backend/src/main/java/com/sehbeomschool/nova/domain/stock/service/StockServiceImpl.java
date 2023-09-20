package com.sehbeomschool.nova.domain.stock.service;

import com.sehbeomschool.nova.domain.stock.dao.MyStocksRepository;
import com.sehbeomschool.nova.domain.stock.dao.StockRepository;
import com.sehbeomschool.nova.domain.stock.dao.StocksInfoRepository;
import com.sehbeomschool.nova.domain.stock.domain.StocksInfo;
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

        for(StocksInfo si : list){
            readStocksListResponseDto dto = readStocksListResponseDto.builder()
                .stockId(si.getStock().getId())
                .stockName(si.getStock().getName())
                .evaluationAmount(si.getPrevPrice())
                .evaluationAmount(si.getCurrentPrice()-si.getPrevPrice())
                .build();

            readStocksListResponseDtoList.add(dto);
        }

        return readStocksListResponseDtoList;
    }

    @Override
    public readStockDetailResponseDto readStockDetail(Long gameId, Long stockId) {
        StocksInfo stocksInfo = stocksInfoRepository.findStocksInfoByGameIdAndStockId(gameId, stockId);
        List<Long> priceList = stocksInfoRepository.findCurrentPricesByGameIdAndStockId(gameId, stockId);
        Long myQuantity = myStocksRepository.findQuantityByGameIdAndStockId(gameId, stockId);

        if(stocksInfo == null) {
            return null;
        }

        readStockDetailResponseDto dto = readStockDetailResponseDto.builder()
            .stockName(stocksInfo.getStock().getName())
            .evaluation(stocksInfo.getCurrentPrice())
            .fluctuations(stocksInfo.getCurrentPrice() - stocksInfo.getPrevPrice())
            .myQuantity(myQuantity)
            .graphValue(priceList)
            .build();
        return dto;
    }
}
