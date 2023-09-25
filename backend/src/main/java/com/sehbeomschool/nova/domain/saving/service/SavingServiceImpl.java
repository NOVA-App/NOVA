package com.sehbeomschool.nova.domain.saving.service;

import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import com.sehbeomschool.nova.domain.saving.dao.InsInterestRepository;
import com.sehbeomschool.nova.domain.saving.dao.SavingRepository;
import com.sehbeomschool.nova.domain.saving.domain.InsInterest;
import com.sehbeomschool.nova.domain.saving.domain.InstallmentSavings;
import com.sehbeomschool.nova.domain.saving.dto.SavingRequestDto.AddInstallmentRequestDto;
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
    private final InsInterestRepository insInterestRepository;

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

    @Override
    public void createInstallment(AddInstallmentRequestDto addInstallmentRequestDto) {

        Game game = gameRepository.findById(addInstallmentRequestDto.getGameId()).orElseThrow();
        InsInterest insInterest = insInterestRepository.findByPeriod(
            addInstallmentRequestDto.getPeriod()).orElseThrow();

        InstallmentSavings installmentSavings = addInstallmentRequestDto.toEntity(game,
            insInterest);
        savingRepository.save(installmentSavings);

        //TODO : annual_cost 에 추가하기
    }

    @Override
    public void deleteInstallment(Long installmentSavingId) {
        InstallmentSavings installmentSavings = savingRepository.findById(installmentSavingId)
            .orElseThrow();

        savingRepository.delete(installmentSavings);
    }
}
