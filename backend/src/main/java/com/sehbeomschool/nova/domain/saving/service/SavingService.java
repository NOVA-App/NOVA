package com.sehbeomschool.nova.domain.saving.service;

import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.SavingInfoResponseDto;

public interface SavingService {

    SavingInfoResponseDto readSavingInfo(Long gameId);

}
