package com.sehbeomschool.nova.domain.stock.service;

import static com.sehbeomschool.nova.domain.game.constant.AssetType.STOCK;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.GAME_NOT_FOUND;

import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.exception.GameNotFoundException;
import com.sehbeomschool.nova.domain.stock.dao.MyStocksRepository;
import com.sehbeomschool.nova.domain.stock.dao.StockRepository;
import com.sehbeomschool.nova.domain.stock.dao.StocksInfoRepository;
import com.sehbeomschool.nova.domain.stock.domain.MyStocks;
import com.sehbeomschool.nova.domain.stock.domain.StocksInfo;
import com.sehbeomschool.nova.domain.stock.dto.StockRequestDto.TradeStockRequestDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.MyStockResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readMyStocksResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStockDetailResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStocksListResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final MyStocksRepository myStocksRepository;
    private final StocksInfoRepository stocksInfoRepository;
    private final StockRepository stockRepository;

    private final GameRepository gameRepository;

    @Override
    public List<readStocksListResponseDto> readStocksList(Long gameId) {
        List<StocksInfo> list = stocksInfoRepository.findStocksInfoListByGameId(gameId);

        List<readStocksListResponseDto> readStocksListResponseDtoList = new ArrayList<>();

        for (StocksInfo si : list) {
            readStocksListResponseDto dto = readStocksListResponseDto.builder()
                .stockId(si.getStock().getId())
                .stockName(si.getStock().getName())
                .evaluationAmount(si.getCurrentPrice())
                .fluctuations(si.calFluctuationsPercent())
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

        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(
            GAME_NOT_FOUND.getMessage()));

        if (myStocks.size() == 0) {
            return null;
        }

        for (MyStocks myStock : myStocks) {
            StocksInfo myStockInfo = stocksInfoRepository.findStocksInfoByAgeIdAndStockId(
                game.getAges().get(game.getAges().size() - 1).getId(), myStock.getStock().getId());

            investAmounts += myStock.getInvestAmount();
            evaluationAmounts += myStockInfo.getCurrentPrice() * myStock.getQuantity();
            Long fluctuation = myStockInfo.getCurrentPrice() - myStockInfo.getPrevPrice();

            MyStockResponseDto myStockResponseDto = MyStockResponseDto.builder()
                .stockId(myStock.getStock().getId())
                .stockName(myStock.getStock().getName())
                .investAmount(myStock.getInvestAmount())
                .evaluationAmount(myStockInfo.getCurrentPrice() * myStock.getQuantity())
                .price(myStockInfo.getCurrentPrice())
                .fluctuations(fluctuation * myStock.getQuantity())
                .fluctuationsPercent(
                    fluctuation * myStock.getQuantity() * 100L / myStock.getInvestAmount())
                .quantity(myStock.getQuantity())
                .build();

            list.add(myStockResponseDto);

        }

        depreciation = evaluationAmounts - investAmounts;
        depreciationPercent = depreciation * 100L / investAmounts;

        readMyStocksResponseDto dto = readMyStocksResponseDto.builder()
            .investAmounts(investAmounts)
            .evaluationAmounts(evaluationAmounts)
            .depreciation(depreciation)
            .depreciationPercent(depreciationPercent)
            .myStocks(list)
            .build();

        return dto;
    }

    @Override
    @Transactional
    public void buyStock(TradeStockRequestDto tradeStockRequestDto) {
        Game game = gameRepository.findById(tradeStockRequestDto.getGameId())
            .orElseThrow(() -> new GameNotFoundException(
                GAME_NOT_FOUND.getMessage()));

        StocksInfo stocksInfo = stocksInfoRepository.findStocksInfoByGameIdAndStockId(
            tradeStockRequestDto.getGameId(),
            tradeStockRequestDto.getStockId());

        Long totalPrice = tradeStockRequestDto.getPurchaseAmount() * stocksInfo.getCurrentPrice();

        for (MyStocks ms : game.getMyStocks()) {
            if (ms.getStock().getId() == tradeStockRequestDto.getStockId()) {
                ms.updateQuantityAndInvestAmountByBuy(tradeStockRequestDto.getPurchaseAmount(),
                    stocksInfo.getCurrentPrice());

                game.getAnnualAsset().useUsableAsset(totalPrice);

                game.getMyAssets().increaseAsset(STOCK, totalPrice);

                game.getMyAssets().recalculateTotalAsset();
                return;
            }
        }

        MyStocks myStocks = MyStocks.builder()
            .stock(stocksInfo.getStock())
            .investAmount(totalPrice)
            .quantity(tradeStockRequestDto.getPurchaseAmount())
            .build();

        myStocksRepository.save(myStocks);

        game.addMyStockAndSetThis(myStocks);
        game.getAnnualAsset().useUsableAsset(totalPrice);
        game.getMyAssets().increaseAsset(STOCK, totalPrice);
        game.getMyAssets().recalculateTotalAsset();
    }

    @Override
    @Transactional
    public void sellStock(TradeStockRequestDto tradeStockRequestDto) {
        Game game = gameRepository.findById(tradeStockRequestDto.getGameId())
            .orElseThrow(() -> new GameNotFoundException(
                GAME_NOT_FOUND.getMessage()));

        StocksInfo stocksInfo = stocksInfoRepository.findStocksInfoByGameIdAndStockId(
            tradeStockRequestDto.getGameId(),
            tradeStockRequestDto.getStockId());

        Long totalPrice = tradeStockRequestDto.getPurchaseAmount() * stocksInfo.getCurrentPrice();

        MyStocks ms;
        for (int i = 0; i < game.getMyStocks().size(); i++) {
            ms = game.getMyStocks().get(i);
            if (ms.getStock().getId() == tradeStockRequestDto.getStockId()) {
                ms.updateQuantityAndInvestAmountBySell(tradeStockRequestDto.getPurchaseAmount());

                game.getAnnualAsset().useUsableAsset(-totalPrice);
                game.getMyAssets().decreaseAsset(STOCK, totalPrice);
                game.getMyAssets().recalculateTotalAsset();

                if (ms.getQuantity() == 0) {
                    game.getMyStocks().remove(ms);
                    i--;
                }
                return;
            }
        }
    }
}
