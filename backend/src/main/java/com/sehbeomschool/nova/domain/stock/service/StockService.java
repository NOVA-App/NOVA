package com.sehbeomschool.nova.domain.stock.service;

import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readMyStocksResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStockDetailResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStocksListResponseDto;
import java.util.List;

public interface StockService {

    List<readStocksListResponseDto> readStocksList(Long gameId);

    readStockDetailResponseDto readStockDetail(Long gameId, Long stockId);

    readMyStocksResponseDto readMyStocks(Long gameId);
}
