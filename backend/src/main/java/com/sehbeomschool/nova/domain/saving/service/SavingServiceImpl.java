package com.sehbeomschool.nova.domain.saving.service;

import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.GAME_NOT_FOUND;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.USABLE_ASSET_NOT_ENOUGH;
import static com.sehbeomschool.nova.domain.saving.constant.SavingExceptionMessage.NOT_ALLOW_MINUS;
import static com.sehbeomschool.nova.domain.saving.constant.SavingExceptionMessage.NOT_EXIST_INSINTEREST;
import static com.sehbeomschool.nova.domain.saving.constant.SavingExceptionMessage.NOT_EXIST_INSTALLMENT;
import static com.sehbeomschool.nova.global.constant.FixedValues.INSTALLMENT_TAX_PERCENTAGE;

import com.sehbeomschool.nova.domain.game.constant.AssetType;
import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.domain.AnnualAsset;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import com.sehbeomschool.nova.domain.game.exception.GameNotFoundException;
import com.sehbeomschool.nova.domain.game.exception.UsableAssetNotEnoughException;
import com.sehbeomschool.nova.domain.saving.dao.InsInterestRepository;
import com.sehbeomschool.nova.domain.saving.dao.SavingRepository;
import com.sehbeomschool.nova.domain.saving.domain.InsInterest;
import com.sehbeomschool.nova.domain.saving.domain.InstallmentSavings;
import com.sehbeomschool.nova.domain.saving.dto.SavingRequestDto.AddInstallmentRequestDto;
import com.sehbeomschool.nova.domain.saving.dto.SavingRequestDto.UpdateIrpRequestDto;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.InstallmentSavingsDto;
import com.sehbeomschool.nova.domain.saving.dto.SavingResponseDto.SavingInfoResponseDto;
import com.sehbeomschool.nova.domain.saving.exception.SavingNotEnoughException;
import com.sehbeomschool.nova.domain.saving.exception.SavingNotFoundException;
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

        Game game = gameRepository.findById(gameId).orElseThrow(
            () -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        MyAssets myAssets = game.getMyAssets();

        return SavingInfoResponseDto.builder().installmentSavings(installmentSavingsDtos)
            .IRPCost(myAssets.getIRPAsset())
            .build();

    }

    @Override
    public void createInstallment(AddInstallmentRequestDto addInstallmentRequestDto) {

        Game game = gameRepository.findById(addInstallmentRequestDto.getGameId()).orElseThrow(
            () -> new GameNotFoundException(GAME_NOT_FOUND.getMessage())
        );
        InsInterest insInterest = insInterestRepository.findByPeriod(
            addInstallmentRequestDto.getPeriod()).orElseThrow(
            () -> new SavingNotFoundException(NOT_EXIST_INSINTEREST.getMessage())
        );

        InstallmentSavings installmentSavings = addInstallmentRequestDto.toEntity(game,
            insInterest);
        savingRepository.save(installmentSavings);

        AnnualAsset annualAsset = game.getAnnualAsset();
        MyAssets myAssets = game.getMyAssets();

        Long amount = installmentSavings.getAmount();
        checkAsset(annualAsset, amount);

        annualAsset.increaseInstallmentSavingCost(amount);
        myAssets.increaseAsset(AssetType.INSTALLMENT_SAVING, installmentSavings.getAmount());
        myAssets.recalculateTotalAsset();
    }

    private static void checkAsset(AnnualAsset annualAsset, Long useMoney) {
        if (annualAsset.getUsableAsset() < useMoney) {
            throw new UsableAssetNotEnoughException(USABLE_ASSET_NOT_ENOUGH.getMessage());
        }
    }

    @Override
    public void deleteInstallment(Long installmentSavingId) {
        InstallmentSavings installmentSavings = savingRepository.findById(installmentSavingId)
            .orElseThrow(
                () -> new SavingNotFoundException(NOT_EXIST_INSTALLMENT.getMessage()));

        Game game = installmentSavings.getGame();
        AnnualAsset annualAsset = game.getAnnualAsset();
        MyAssets myAssets = game.getMyAssets();

        annualAsset.decreaseInstallmentSavingCost(installmentSavings.getAmount());
        myAssets.decreaseAsset(AssetType.INSTALLMENT_SAVING, installmentSavings.getTotalAmount());

        savingRepository.delete(installmentSavings);
    }

    @Override
    public void matureInstallment(Long installmentSavingId) {
        InstallmentSavings installmentSavings = savingRepository.findById(installmentSavingId)
            .orElseThrow(() -> new SavingNotFoundException(NOT_EXIST_INSTALLMENT.getMessage()));

        InsInterest interest = installmentSavings.getInterest();
        double compoundInterest = calculateCompoundInterest(installmentSavings.getAmount(),
            (double) interest.getInterest() / 100,
            interest.getPeriod());
        double tax = compoundInterest * INSTALLMENT_TAX_PERCENTAGE.getValue() / 100;

        Game game = installmentSavings.getGame();
        AnnualAsset annualAsset = game.getAnnualAsset();
        MyAssets myAssets = game.getMyAssets();

        annualAsset.decreaseInstallmentSavingCost(installmentSavings.getAmount());

        annualAsset.earnAsset((long) (compoundInterest - tax));
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
        Game game = gameRepository.findById(gameId).orElseThrow(
            () -> new GameNotFoundException(GAME_NOT_FOUND.getMessage())
        );
        List<InstallmentSavings> installmentSavings = savingRepository.findByGameId(gameId)
            .orElseThrow();
        for (InstallmentSavings installmentSaving : installmentSavings) {
            if (installmentSaving.getEndAge() == game.getCurrentAge()) {
                matureInstallment(installmentSaving.getId());
                continue;
            }
            installmentSaving.updateTotalAmountForNextYear();

            game.getMyAssets()
                .increaseAsset(AssetType.INSTALLMENT_SAVING, installmentSaving.getAmount());
        }
    }

    @Override
    public void updateIrpCost(UpdateIrpRequestDto updateIrpRequestDto) {
        if (updateIrpRequestDto.getIrpCost() < 0) {
            throw new SavingNotEnoughException(NOT_ALLOW_MINUS.getMessage());
        }

        Game game = gameRepository.findById(updateIrpRequestDto.getGameId()).orElseThrow(
            () -> new GameNotFoundException(GAME_NOT_FOUND.getMessage())
        );

        AnnualAsset annualAsset = game.getAnnualAsset();
        Long diff = updateIrpRequestDto.getIrpCost() - annualAsset.getIRPCost();

        checkAsset(annualAsset, diff);
        annualAsset.updateIRPCost(updateIrpRequestDto.getIrpCost());

    }


    @Override
    public void updateIrpForNextYear(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
            () -> new GameNotFoundException(GAME_NOT_FOUND.getMessage())
        );
        MyAssets myAssets = game.getMyAssets();
        AnnualAsset annualAsset = game.getAnnualAsset();

        Long irpInterest = RandomCalculator.calIrpInterest(myAssets.getIRPAsset());
        Long irpCost = annualAsset.getIRPCost();

        myAssets.increaseAsset(AssetType.IRP, irpInterest + irpCost);
    }

    @Override
    public void deleteInstallmentByGameId(Long gameId) {
        savingRepository.deleteByGameId(gameId);
    }

}
