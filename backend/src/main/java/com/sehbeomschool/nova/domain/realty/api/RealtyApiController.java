package com.sehbeomschool.nova.domain.realty.api;

import com.sehbeomschool.nova.domain.realty.constant.RealtyResponseMessage;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyDetailResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyResponseDto;
import com.sehbeomschool.nova.domain.realty.service.RealtyService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
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
                RealtyResponseMessage.READ_MY_REATIES.getMessage(),
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
}
