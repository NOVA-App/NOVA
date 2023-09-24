package com.sehbeomschool.nova.domain.saving.api;

import com.sehbeomschool.nova.domain.saving.constant.SavingResponseMessage;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.SavingInfoResponseDto;
import com.sehbeomschool.nova.domain.saving.service.SavingService;
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
@RequestMapping("/api/saving")
public class SavingApiController {

    private final SavingService savingService;

    @GetMapping("/{gameId}")
    public ResponseEntity<ResponseDto<SavingInfoResponseDto>> getSavingInfo(
        @PathVariable Long gameId) {

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SavingResponseMessage.SUCCESS_GET_SAVING_INFO.getMessage(),
                savingService.readSavingInfo(gameId)));

    }
}
