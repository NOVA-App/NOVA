package com.sehbeomschool.nova.domain.saving.service;

import static com.sehbeomschool.nova.global.constant.FixedValues.INSTALLMENT_TAX_PERCENTAGE;

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
import com.sehbeomschool.nova.domain.saving.dto.SavingRequestDto.UpdateIrpRequestDto;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.InstallmentSavingsDto;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.SavingInfoResponseDto;
import com.sehbeomschool.nova.global.util.RandomCalculator;
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
        myAssets.recalculateTotalAsset();
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

        InsInterest interest = installmentSavings.getInterest();
        double compoundInterest = calculateCompoundInterest(installmentSavings.getAmount(),
            (double) interest.getInterest() / 100,
            interest.getPeriod());
        double tax = compoundInterest * INSTALLMENT_TAX_PERCENTAGE.getValue() / 100;

        System.out.println("이자 : " + compoundInterest);

        Game game = installmentSavings.getGame();
        AnnualAsset annualAsset = game.getAnnualAsset();
        MyAssets myAssets = game.getMyAssets();
        //TODO : annual_cost 에 추가하기

        //annualAsset.deleteInstallmentSaving(installmentSavings.getAmount());

        annualAsset.earnAsset(
            installmentSavings.getTotalAmount() + (long) (compoundInterest - tax));
        myAssets.decreaseAsset(AssetType.INSTALLMENT_SAVING, installmentSavings.getTotalAmount());
        myAssets.increaseAsset(AssetType.TAX, (long) tax);

        savingRepository.delete(installmentSavings);
    }

    public static double calculateCompoundInterest(Long principal, double annualInterestRate,
        int years) {
        return principal * Math.pow(1 + annualInterestRate, years) - principal;
    }

    @Override
    public void updateInstallmentForNextYear(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        List<InstallmentSavings> installmentSavings = savingRepository.findByGameId(gameId)
            .orElseThrow();
        for (InstallmentSavings installmentSaving : installmentSavings) {
            if (installmentSaving.getEndAge() - 1 == game.getCurrentAge()) {
                matureInstallment(installmentSaving.getId());
                continue;
            }
            installmentSaving.updateTotalAmountForNextYear();
            //TODO : 연간 적금 자산 증가 시키기
            game.getMyAssets()
                .increaseAsset(AssetType.INSTALLMENT_SAVING, installmentSaving.getAmount());
        }
    }

    @Override
    public void updateIrpCost(UpdateIrpRequestDto updateIrpRequestDto) {
        if (updateIrpRequestDto.getIrpCost() < 0) {
            //예외처리
        }
        Game game = gameRepository.findById(updateIrpRequestDto.getGameId()).orElseThrow();
        AnnualAsset annualAsset = game.getAnnualAsset();
        //TODO : 연간 IRP 비용 업데이트
        MyAssets myAssets = game.getMyAssets();
        Long diff = updateIrpRequestDto.getIrpCost() - annualAsset.getIRPCost();

        myAssets.increaseAsset(AssetType.IRP, diff);
        myAssets.recalculateTotalAsset();
    }

    @Override
    public void updateIrpForNextYear(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        MyAssets myAssets = game.getMyAssets();

        Long irpInterest = RandomCalculator.calIrpInterest(myAssets.getIRPAsset());
        Long irpCost = game.getAnnualAsset().getIRPCost();

        //TODO : 연간 자산 업데이트
        myAssets.increaseAsset(AssetType.IRP, irpInterest + irpCost);
    }

}
