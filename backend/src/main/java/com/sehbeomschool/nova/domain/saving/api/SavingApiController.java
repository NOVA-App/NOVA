package com.sehbeomschool.nova.domain.saving.api;

import static com.sehbeomschool.nova.domain.saving.constant.SavingResponseMessage.SUCCESS_ADD_INSTALLMENT;
import static com.sehbeomschool.nova.domain.saving.constant.SavingResponseMessage.SUCCESS_GET_SAVING_INFO;

import com.sehbeomschool.nova.domain.saving.dto.SavingRequestDto.AddInstallmentRequestDto;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.SavingInfoResponseDto;
import com.sehbeomschool.nova.domain.saving.service.SavingService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            ResponseDto.create(SUCCESS_GET_SAVING_INFO.getMessage(),
                savingService.readSavingInfo(gameId)));

    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<SavingInfoResponseDto>> addInstallment(
        @RequestBody AddInstallmentRequestDto addInstallmentRequestDto) {

        savingService.createInstallment(addInstallmentRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SUCCESS_ADD_INSTALLMENT.getMessage()));

    }
}
