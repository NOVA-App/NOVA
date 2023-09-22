package com.sehbeomschool.nova.domain.realty.api;

import com.sehbeomschool.nova.domain.realty.constant.RealtyResponseMessage;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
                RealtyResponseMessage.READ_REALTY.getMessage(),
                realtyService.readRealtyDetail(gameId, realtyId)
            )
        );
    }
}
