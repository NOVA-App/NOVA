package com.sehbeomschool.nova.domain.saving.service;

import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import com.sehbeomschool.nova.domain.saving.dao.SavingRepository;
import com.sehbeomschool.nova.domain.saving.domain.InstallmentSavings;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.InstallmentSavingsDto;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.SavingInfoResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SavingServiceImpl implements SavingService {

    private final SavingRepository savingRepository;
    private final GameRepository gameRepository;

    @Override
    public SavingInfoResponseDto readSavingInfo(Long gameId) {
        List<InstallmentSavings> installmentSavings = savingRepository.findByGameId(gameId)
            .orElseThrow();

        List<InstallmentSavingsDto> installmentSavingsDtos = installmentSavings.stream()
            .map(InstallmentSavingsDto::new).toList();

        Game game = gameRepository.findById(gameId).orElseThrow();
        MyAssets myAssets = game.getMyAssets();

        return SavingInfoResponseDto.builder().installmentSavings(installmentSavingsDtos)
            .IRPCost(myAssets.getIRPAsset())
            .build();

    }
}
