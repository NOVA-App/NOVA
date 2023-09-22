package com.sehbeomschool.nova.domain.realty.service;

import com.sehbeomschool.nova.domain.realty.dao.MyRealtyRepository;
import com.sehbeomschool.nova.domain.realty.dao.RealtyInfoRepository;
import com.sehbeomschool.nova.domain.realty.dao.RealtyRepository;
import com.sehbeomschool.nova.domain.realty.domain.MyRealty;
import com.sehbeomschool.nova.domain.realty.domain.RealtyInfo;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.MyRealtyResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyDetailResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealtyServiceImpl implements RealtyService {

    private final RealtyRepository realtyRepository;
    private final RealtyInfoRepository realtyInfoRepository;
    private final MyRealtyRepository myRealtyRepository;

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

        if(myRealty == null || realtyInfo == null){
            return null;
        }

        Long depreciationPercent =
            (realtyInfo.getCurrentPrice() - myRealty.getInvestAmount()) / myRealty.getInvestAmount()
                * 100;

        ReadMyRealtyDetailResponseDto dto = ReadMyRealtyDetailResponseDto.builder()
            .realtyId(realtyId)
            .realtyName(myRealty.getRealty().getName())
            .realtyImg(myRealty.getRealty().getRealtyImg())
            .investAmount(myRealty.getInvestAmount())
            .evaluationAmount(realtyInfo.getCurrentPrice())
            .depreciationPercent(depreciationPercent)
            .rentIncome(myRealty.getRentIncome())
            .principal(myRealty.getLoan().getPrincipal())
            .build();

        return dto;
    }
}
