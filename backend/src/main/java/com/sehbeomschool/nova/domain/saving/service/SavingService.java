package com.sehbeomschool.nova.domain.saving.service;

import com.sehbeomschool.nova.domain.saving.dto.SavingRequestDto.AddInstallmentRequestDto;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.SavingInfoResponseDto;

public interface SavingService {

    SavingInfoResponseDto readSavingInfo(Long gameId);

    void createInstallment(AddInstallmentRequestDto addInstallmentRequestDto);
    void deleteInstallment(Long installmentSavingId);
    void matureInstallment(Long installmentSavingId);
    void updateInstallmentByNextYear(Long gameId);

}
