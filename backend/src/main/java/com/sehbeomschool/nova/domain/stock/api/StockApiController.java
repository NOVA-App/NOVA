package com.sehbeomschool.nova.domain.stock.api;

import static com.sehbeomschool.nova.domain.stock.constant.StockResponseMessage.BUY_STOCK;
import static com.sehbeomschool.nova.domain.stock.constant.StockResponseMessage.READ_MY_STOCKS;
import static com.sehbeomschool.nova.domain.stock.constant.StockResponseMessage.READ_STOCKS_LIST;
import static com.sehbeomschool.nova.domain.stock.constant.StockResponseMessage.READ_STOCK_DETAIL;
import static com.sehbeomschool.nova.domain.stock.constant.StockResponseMessage.SELL_STOCK;

import com.sehbeomschool.nova.domain.stock.dto.StockRequestDto.TradeStockRequestDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readMyStocksResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStockDetailResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStocksListResponseDto;
import com.sehbeomschool.nova.domain.stock.service.StockService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockApiController {

    private final StockService stockService;

    @GetMapping("/mine/{gameId}")
    public ResponseEntity<ResponseDto<readMyStocksResponseDto>> readMyStocks(
        @PathVariable(value = "gameId") Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_MY_STOCKS.getMessage(),
                stockService.readMyStocks(gameId)
            )
        );
    }


    @GetMapping("/list/{gameId}")
    public ResponseEntity<ResponseDto<List<readStocksListResponseDto>>> readStocksList(
        @PathVariable(value = "gameId") Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_STOCKS_LIST.getMessage(),
                stockService.readStocksList(gameId)
            )
        );
    }

    @GetMapping("/{gameId}/{stockId}")
    public ResponseEntity<ResponseDto<readStockDetailResponseDto>> readStockDetail(
        @PathVariable(value = "gameId") Long gameId,
        @PathVariable(value = "stockId") Long stockId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_STOCK_DETAIL.getMessage(),
                stockService.readStockDetail(gameId, stockId)
            )
        );
    }

    @PostMapping("/buy")
    public ResponseEntity<ResponseDto<?>> buyStock(
        @RequestBody TradeStockRequestDto tradeStockRequestDto) {
        stockService.buyStock(tradeStockRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                BUY_STOCK.getMessage()
            )
        );
    }

    @PatchMapping("/sell")
    public ResponseEntity<ResponseDto<?>> sellStock(
        @RequestBody TradeStockRequestDto tradeStockRequestDto) {
        stockService.sellStock(tradeStockRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                SELL_STOCK.getMessage()
            )
        );
    }

}
