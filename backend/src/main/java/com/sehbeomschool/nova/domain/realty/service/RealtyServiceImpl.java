package com.sehbeomschool.nova.domain.realty.service;

import static com.sehbeomschool.nova.domain.game.constant.AssetType.LOAN;
import static com.sehbeomschool.nova.domain.game.constant.AssetType.REALTY;
import static com.sehbeomschool.nova.domain.game.constant.AssetType.TAX;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.GAME_NOT_FOUND;
import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.USABLE_ASSET_NOT_ENOUGH;
import static com.sehbeomschool.nova.global.util.TaxCalculator.calRealtyAcquistionTax;

import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.exception.GameNotFoundException;
import com.sehbeomschool.nova.domain.game.exception.UsableAssetNotEnoughException;
import com.sehbeomschool.nova.domain.realty.dao.LoanRepository;
import com.sehbeomschool.nova.domain.realty.dao.MyRealtyRepository;
import com.sehbeomschool.nova.domain.realty.dao.RealtyInfoRepository;
import com.sehbeomschool.nova.domain.realty.dao.RealtyRepository;
import com.sehbeomschool.nova.domain.realty.domain.Loan;
import com.sehbeomschool.nova.domain.realty.domain.MyRealty;
import com.sehbeomschool.nova.domain.realty.domain.RealtyInfo;
import com.sehbeomschool.nova.domain.realty.dto.RealtyRequestDto.RepaymentLoanRequestDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyRequestDto.TradeRealtyRequestDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.MyRealtyResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadLoanListResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyDetailResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadRealtyDetailResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadRealtyResponseDto;
import com.sehbeomschool.nova.global.util.TaxCalculator;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RealtyServiceImpl implements RealtyService {

    private final RealtyRepository realtyRepository;
    private final RealtyInfoRepository realtyInfoRepository;
    private final MyRealtyRepository myRealtyRepository;
    private final LoanRepository loanRepository;

    private final GameRepository gameRepository;

    @Override
    public ReadMyRealtyResponseDto readMyRealty(Long gameId) {
        Long investAmounts = 0L;
        Long evaluationAmounts = 0L;
        Long totalRentalIncome = 0L;

        List<MyRealty> myRealties = myRealtyRepository.findMyRealtiesByGameId(gameId);

        List<MyRealtyResponseDto> list = new ArrayList<>();

        for (MyRealty mr : myRealties) {
            RealtyInfo ri = realtyInfoRepository.findRealtyInfoByGameIdAndRealtyId(gameId,
                mr.getRealty().getId());

            investAmounts += mr.getInvestAmount();
            evaluationAmounts += ri.getCurrentPrice();
            totalRentalIncome += ri.getPredictedRentIncome();

            MyRealtyResponseDto myRealtyResponseDto = MyRealtyResponseDto.builder()
                .realtyId(mr.getRealty().getId())
                .realtyName(mr.getRealty().getName())
                .realtyImg(mr.getRealty().getRealtyImg())
                .investAmount(mr.getInvestAmount())
                .evaluationAmount(ri.getCurrentPrice())
                .reantalIncome(ri.getPredictedRentIncome())
                .build();

            list.add(myRealtyResponseDto);
        }

        ReadMyRealtyResponseDto dto = ReadMyRealtyResponseDto.builder()
            .investAmounts(investAmounts)
            .evaluationAmounts(evaluationAmounts)
            .totalRentalIncome(totalRentalIncome)
            .myRealties(list)
            .build();

        return dto;
    }

    @Override
    public ReadMyRealtyDetailResponseDto readMyRealtyDetail(Long gameId, Long realtyId) {
        MyRealty myRealty = myRealtyRepository.findMyRealtyByGameIdAndRealtyId(gameId, realtyId);
        RealtyInfo realtyInfo = realtyInfoRepository.findRealtyInfoByGameIdAndRealtyId(gameId,
            realtyId);

        if (myRealty == null || realtyInfo == null) {
            return null;
        }

        ReadMyRealtyDetailResponseDto dto = ReadMyRealtyDetailResponseDto.builder()
            .realtyId(realtyId)
            .realtyName(myRealty.getRealty().getName())
            .realtyImg(myRealty.getRealty().getRealtyImg())
            .investAmount(myRealty.getInvestAmount())
            .evaluationAmount(realtyInfo.getCurrentPrice())
            .depreciationPercent(myRealty.calDepreciationPercent(realtyInfo.getCurrentPrice()))
            .rentIncome(myRealty.getRentIncome())
            .principal(myRealty.getLoan() == null ? 0 : myRealty.getLoan().getPrincipal())
            .build();

        return dto;
    }

    @Override
    public List<ReadRealtyResponseDto> readRealtyList(Long gameId) {
        List<RealtyInfo> realtyInfoList = realtyInfoRepository.findRealtyInfosByGameIdAndNotinMyRealty(
            gameId);

        List<ReadRealtyResponseDto> list = new ArrayList<>();

        for (RealtyInfo ri : realtyInfoList) {
            ReadRealtyResponseDto dto = ReadRealtyResponseDto.builder()
                .realtyId(ri.getRealty().getId())
                .realtyName(ri.getRealty().getName())
                .realtyImg(ri.getRealty().getRealtyImg())
                .region(ri.getRealty().getRegion())
                .depreciationPercent(ri.calDepreciationPercent())
                .evaluationAmount(ri.getCurrentPrice())
                .predictedRentIncome(ri.getPredictedRentIncome())
                .build();

            list.add(dto);
        }

        return list;
    }

    @Override
    public ReadRealtyDetailResponseDto readRealtyDetail(Long gameId, Long realtyId) {
        RealtyInfo realtyInfo = realtyInfoRepository.findRealtyInfoByGameIdAndRealtyId(gameId,
            realtyId);

        Long myCount = myRealtyRepository.countByGameId(gameId);

        if (realtyInfo == null) {
            return null;
        }

        ReadRealtyDetailResponseDto dto = ReadRealtyDetailResponseDto.builder()
            .realtyId(realtyId)
            .realtyName(realtyInfo.getRealty().getName())
            .realtyImg(realtyInfo.getRealty().getRealtyImg())
            .depreciationPercent(realtyInfo.calDepreciationPercent())
            .region(realtyInfo.getRealty().getRegion())
            .predictedRentIncome(realtyInfo.getPredictedRentIncome())
            .totalPrice(TaxCalculator.calRealtyTotalPrice(
                realtyInfo.getCurrentPrice(), myCount))
            .evaluationAmount(realtyInfo.getCurrentPrice())
            .acquistionTax(
                calRealtyAcquistionTax(realtyInfo.getCurrentPrice(), myCount))
            .enableLoanAmount(realtyInfo.calEnableLoanAmount(myCount))
            .build();

        return dto;
    }

    @Override
    @Transactional
    public void buyRealty(TradeRealtyRequestDto tradeRealtyRequestDto) {
        Game game = gameRepository.findById(tradeRealtyRequestDto.getGameId())
            .orElseThrow(() -> new GameNotFoundException(
                GAME_NOT_FOUND.getMessage()));

        RealtyInfo realtyInfo = realtyInfoRepository.findRealtyInfoByGameIdAndRealtyId(game.getId(),
            tradeRealtyRequestDto.getRealtyId());

        Loan loan = null;

        Long aquisitionTax = calRealtyAcquistionTax(realtyInfo.getCurrentPrice(),
            Long.valueOf(game.getMyRealties().size()));

        if (tradeRealtyRequestDto.getPrincipalAmount() != 0) {
            loan = Loan.builder()
                .principal(tradeRealtyRequestDto.getPrincipalAmount())
                .build();

            loanRepository.save(loan);
        }

        MyRealty myRealty = MyRealty.builder()
            .investAmount(realtyInfo.getCurrentPrice())
            .rentIncome(realtyInfo.getCurrentPrice() / 20)
            .realty(realtyInfo.getRealty())
            .loan(loan == null ? null : loan)
            .build();

        game.addMyRealtyAndSetThis(myRealty);

        myRealtyRepository.save(myRealty);

        game.getAnnualAsset().useUsableAsset(
            realtyInfo.getCurrentPrice() + aquisitionTax
                - tradeRealtyRequestDto.getPrincipalAmount());

        game.getMyAssets().increaseAsset(REALTY, realtyInfo.getCurrentPrice());
        game.getMyAssets().increaseAsset(LOAN, tradeRealtyRequestDto.getPrincipalAmount());
        game.getMyAssets().increaseAsset(TAX, aquisitionTax);
    }

    @Override
    @Transactional
    public void sellRealty(Long gameId, Long realtyId) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new GameNotFoundException(
                GAME_NOT_FOUND.getMessage()));

        for (int i = 0; i < game.getMyRealties().size(); i++) {
            if (game.getMyRealties().get(i).getRealty().getId() == realtyId) {
                RealtyInfo ri = realtyInfoRepository.findRealtyInfoByGameIdAndRealtyId(gameId,
                    realtyId);
                MyRealty mr = game.getMyRealties().get(i);

                Long aquisitionTax = calRealtyAcquistionTax(ri.getCurrentPrice(),
                    Long.valueOf(game.getMyRealties().size()));
                Long totalPrice = ri.getCurrentPrice() - aquisitionTax;

                if (mr.getLoan() != null
                    && ri.getCurrentPrice() >= mr.getLoan().getPrincipal() + aquisitionTax) {
                    totalPrice -= mr.getLoan().getPrincipal();
                } else if (game.getAnnualAsset().getUsableAsset() + ri.getCurrentPrice()
                    >= mr.getLoan().getPrincipal() + aquisitionTax) {
                    totalPrice = -(totalPrice - mr.getLoan().getPrincipal());
                } else {
                    throw new UsableAssetNotEnoughException(USABLE_ASSET_NOT_ENOUGH.getMessage());
                }

                game.getAnnualAsset().useUsableAsset(-totalPrice);
                game.getMyAssets().decreaseAsset(REALTY, ri.getCurrentPrice());
                game.getMyAssets().decreaseAsset(LOAN, mr.getLoan().getPrincipal());
                game.getMyAssets().increaseAsset(TAX, aquisitionTax);
                game.getMyRealties().remove(i);
                return;
            }
        }
    }

    @Override
    public List<ReadLoanListResponseDto> readLoan(Long gameId) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new GameNotFoundException(
                GAME_NOT_FOUND.getMessage()));

        List<ReadLoanListResponseDto> list = new ArrayList<>();

        for (MyRealty mr : game.getMyRealties()) {
            RealtyInfo ri = realtyInfoRepository.findRealtyInfoByGameIdAndRealtyId(gameId,
                mr.getRealty().getId());

            if (mr.getLoan() != null) {
                ReadLoanListResponseDto dto = ReadLoanListResponseDto.builder()
                    .realtyId(mr.getRealty().getId())
                    .loanId(mr.getLoan().getId())
                    .realtyName(mr.getRealty().getName())
                    .realtyPrice(ri.getCurrentPrice())
                    .principal(mr.getLoan().getPrincipal())
                    .realtyImg(mr.getRealty().getRealtyImg())
                    .build();

                list.add(dto);
            }
        }

        return list;
    }

    @Override
    @Transactional
    public void repaymentLoan(RepaymentLoanRequestDto repaymentLoanRequestDto) {
        MyRealty myRealty = myRealtyRepository.findMyRealtyByGameIdAndRealtyId(
            repaymentLoanRequestDto.getGameId(), repaymentLoanRequestDto.getRealtyId());

        Game game = myRealty.getGame();

        myRealty.repaymentLoan(repaymentLoanRequestDto.getPrincipalAmount());

        game.getAnnualAsset().useUsableAsset(repaymentLoanRequestDto.getPrincipalAmount());
        game.getMyAssets().decreaseAsset(LOAN, repaymentLoanRequestDto.getPrincipalAmount());
    }
}
