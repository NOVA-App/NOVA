package com.sehbeomschool.nova.domain.realty.api;

import static com.sehbeomschool.nova.domain.realty.constant.RealtyResponseMessage.BUY_REALTY;
import static com.sehbeomschool.nova.domain.realty.constant.RealtyResponseMessage.READ_LOAN;
import static com.sehbeomschool.nova.domain.realty.constant.RealtyResponseMessage.READ_REALTY;
import static com.sehbeomschool.nova.domain.realty.constant.RealtyResponseMessage.REPAYMENT_LOAN;
import static com.sehbeomschool.nova.domain.realty.constant.RealtyResponseMessage.SELL_REALTY;

import com.sehbeomschool.nova.domain.realty.constant.RealtyResponseMessage;
import com.sehbeomschool.nova.domain.realty.dto.RealtyRequestDto.RepaymentLoanRequestDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyRequestDto.TradeRealtyRequestDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadLoanListResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyDetailResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadRealtyDetailResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadRealtyResponseDto;
import com.sehbeomschool.nova.domain.realty.service.RealtyService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/realty")
public class RealtyApiController {

    private final RealtyService realtyService;

    @GetMapping("/mine/{gameId}")
    public ResponseEntity<ResponseDto<ReadMyRealtyResponseDto>> readMyRealties(
        @PathVariable(value = "gameId") Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                RealtyResponseMessage.READ_MY_REALTIES.getMessage(),
                realtyService.readMyRealty(gameId)
            )
        );
    }

    @GetMapping("/mine/{gameId}/{realtyId}")
    public ResponseEntity<ResponseDto<ReadMyRealtyDetailResponseDto>> readMyRealtyDetail(
        @PathVariable(value = "gameId") Long gameId,
        @PathVariable(value = "realtyId") Long realtyId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                RealtyResponseMessage.READ_MY_REALTY_DETAIL.getMessage(),
                realtyService.readMyRealtyDetail(gameId, realtyId)
            )
        );
    }

    @GetMapping("/list/{gameId}")
    public ResponseEntity<ResponseDto<List<ReadRealtyResponseDto>>> readRealties(
        @PathVariable(value = "gameId") Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                RealtyResponseMessage.READ_REALTIES.getMessage(),
                realtyService.readRealtyList(gameId)
            )
        );
    }

    @GetMapping("/{gameId}/{realtyId}")
    public ResponseEntity<ResponseDto<ReadRealtyDetailResponseDto>> readRealty(
        @PathVariable(value = "gameId") Long gameId,
        @PathVariable(value = "realtyId") Long realtyId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_REALTY.getMessage(),
                realtyService.readRealtyDetail(gameId, realtyId)
            )
        );
    }

    @PostMapping("/buy")
    public ResponseEntity<ResponseDto<?>> buyRealty(
        @RequestBody TradeRealtyRequestDto tradeRealtyRequestDto) {
        realtyService.buyRealty(tradeRealtyRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                BUY_REALTY.getMessage()
            )
        );
    }

    @DeleteMapping("/sell/{gameId}/{realtyId}")
    public ResponseEntity<ResponseDto<?>> sellRealty(@PathVariable(value = "gameId") Long gameId,
        @PathVariable(value = "realtyId") Long realtyId) {
        realtyService.sellRealty(gameId, realtyId);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                SELL_REALTY.getMessage()
            )
        );
    }

    @GetMapping("/loan/{gameId}")
    public ResponseEntity<ResponseDto<List<ReadLoanListResponseDto>>> readLoan(
        @PathVariable(value = "gameId") Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_LOAN.getMessage(),
                realtyService.readLoan(gameId)
            )
        );
    }

    @PatchMapping("/loan/repayment")
    public ResponseEntity<ResponseDto<?>> repaymentLoan(
        @RequestBody RepaymentLoanRequestDto repaymentLoanRequestDto) {
        realtyService.repaymentLoan(repaymentLoanRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                REPAYMENT_LOAN.getMessage()
            )
        );
    }
}
