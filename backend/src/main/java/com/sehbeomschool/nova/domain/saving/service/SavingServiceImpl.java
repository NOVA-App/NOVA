package com.sehbeomschool.nova.domain.saving.service;

import com.sehbeomschool.nova.domain.game.constant.AssetType;
import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.domain.AnnualAsset;
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

        AnnualAsset annualAsset = game.getAnnualAsset();
        MyAssets myAssets = game.getMyAssets();
        //TODO : annual_cost 에 추가하기
        // annualAsset.addInstallmentSaving(installmentSavings.getAmount());
        myAssets.increaseAsset(AssetType.INSTALLMENT_SAVING, installmentSavings.getAmount());

    }

    @Override
    public void deleteInstallment(Long installmentSavingId) {
        InstallmentSavings installmentSavings = savingRepository.findById(installmentSavingId)
            .orElseThrow();

        Game game = installmentSavings.getGame();
        AnnualAsset annualAsset = game.getAnnualAsset();
        MyAssets myAssets = game.getMyAssets();
        //TODO : annual_cost 에 추가하기

        // annualAsset.deleteInstallmentSaving(installmentSavings.getAmount());
        annualAsset.earnAsset(installmentSavings.getTotalAmount());
        myAssets.decreaseAsset(AssetType.INSTALLMENT_SAVING, installmentSavings.getTotalAmount());

        savingRepository.delete(installmentSavings);
    }

    @Override
    public void matureInstallment(Long installmentSavingId) {
        InstallmentSavings installmentSavings = savingRepository.findById(installmentSavingId)
            .orElseThrow();

        Game game = installmentSavings.getGame();
        AnnualAsset annualAsset = game.getAnnualAsset();
        MyAssets myAssets = game.getMyAssets();
        //TODO : annual_cost 에 추가하기

        //annualAsset.deleteInstallmentSaving(installmentSavings.getAmount());
        annualAsset.earnAsset(installmentSavings.getTotalAmount());

        InsInterest interest = installmentSavings.getInterest();
        double CompoundInterest = calculateCompoundInterest(installmentSavings.getAmount(),
            interest.getInterest(),
            interest.getPeriod());
        //myAssets.increaseAsset(AssetType);
    }

    public static double calculateCompoundInterest(Long principal, int annualInterestRate,
        int years) {
        double compoundInterest = principal * Math.pow(1 + annualInterestRate, years) - principal;
        return compoundInterest;
    }

    @Override
    public void updateInstallmentByNextYear(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        List<InstallmentSavings> installmentSavings = savingRepository.findByGameId(gameId)
            .orElseThrow();
        for (InstallmentSavings installmentSaving : installmentSavings) {

        }
    }

//    @Override
//    public void updateInstallmentByCurrentYear(Long gameId) {
//        Game game = gameRepository.findById(gameId).orElseThrow();
//        List<InstallmentSavings> installmentSavings = savingRepository.findByGameId(gameId)
//            .orElseThrow();
//        for (InstallmentSavings installmentSaving : installmentSavings) {
//            if(installmentSaving.getEndAge() == game.getCurrentAge()){
//                matureInstallment(installmentSaving.getId());
//            }
//        }
//    }
}
