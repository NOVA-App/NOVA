package com.sehbeomschool.nova.domain.stock.api;

import com.sehbeomschool.nova.domain.stock.constant.StockResponseMessage;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStockDetailResponseDto;
import com.sehbeomschool.nova.domain.stock.dto.StockResponseDto.readStocksListResponseDto;
import com.sehbeomschool.nova.domain.stock.service.StockService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockApiController {

    private final StockService stockService;

    @GetMapping("/list/{gameId}")
    public ResponseEntity<ResponseDto<List<readStocksListResponseDto>>> readStocksList(
        @PathVariable Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                StockResponseMessage.READ_STOCKS_LIST.getMessage(),
                stockService.readStocksList(gameId)
            )
        );
    }

    @GetMapping("/detail/{gameId}/{stockId}")
    public ResponseEntity<ResponseDto<readStockDetailResponseDto>> readStockDetail(
        @PathVariable(value = "gameId") Long gameId,
        @PathVariable(value = "stockId") Long stockId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                StockResponseMessage.READ_STOCK_DETAIL.getMessage(),
                stockService.readStockDetail(gameId, stockId)
            )
        );
    }

}
