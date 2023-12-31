package com.sehbeomschool.nova.domain.realty.service;

import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.realty.dao.MyRealtyRepository;
import com.sehbeomschool.nova.domain.realty.dao.RealtyInfoRepository;
import com.sehbeomschool.nova.domain.realty.dao.RealtyRepository;
import com.sehbeomschool.nova.domain.realty.domain.MyRealty;
import com.sehbeomschool.nova.domain.realty.domain.Realty;
import com.sehbeomschool.nova.domain.realty.domain.RealtyInfo;
import com.sehbeomschool.nova.global.util.RandomCalculator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RealtyManagerServiceImpl implements RealtyManagerService {

    private final RealtyRepository realtyRepository;
    private final RealtyInfoRepository realtyInfoRepository;
    private final MyRealtyRepository myRealtyRepository;

    @Override
    @Transactional
    public void createRealtyInfoByGameStart(Game game) {
        List<Realty> list = realtyRepository.findAll();

        for (Realty r : list) {
            Long currentPrice = RandomCalculator.calRealty(r.getStartPrice());

            RealtyInfo ri = RealtyInfo.builder()
                .id(null)
                .game(game)
                .realty(r)
                .prevPrice(r.getStartPrice())
                .currentPrice(currentPrice)
                .nextPrice(RandomCalculator.calRealty(currentPrice))
                .predictedRentIncome(r.getStartPrice() / 20)
                .build();

            realtyInfoRepository.save(ri);
        }
    }

    @Override
    @Transactional
    public void updateRealtyInfoByNextYear(Long gameId) {
        List<RealtyInfo> list = realtyInfoRepository.findRealtyInfoByGameId(gameId);

        for (RealtyInfo ri : list) {
            Long nextPrice = RandomCalculator.calRealty(ri.getNextPrice());

            ri.setNextYearPrice(nextPrice);
        }
    }

    @Override
    @Transactional
    public void updateMyRealtyByNextYear(Game game) {
        List<MyRealty> list = game.getMyRealties();

        for (MyRealty mr : list) {
            RealtyInfo ri = realtyInfoRepository.findRealtyInfoByGameIdAndRealtyId(game.getId(),
                mr.getRealty().getId());

            mr.setRentIncome(ri.getCurrentPrice());
        }
    }

    @Override
    @Transactional
    public void deleteRealtyInfo(Long gameId) {
        realtyInfoRepository.deleteRealtyInfoByGameIdInQuery(gameId);
    }

    @Override
    public Long calRealtyAssetForNextYear(Game game) {
        Long realtyAsset = 0L;

        for (MyRealty mr : game.getMyRealties()) {
            RealtyInfo ri = realtyInfoRepository.findRealtyInfoByGameIdAndRealtyId(game.getId(),
                mr.getRealty().getId());

            realtyAsset += ri.getCurrentPrice();
        }

        return realtyAsset;
    }
}
