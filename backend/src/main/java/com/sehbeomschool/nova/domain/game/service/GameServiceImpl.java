package com.sehbeomschool.nova.domain.game.service;

import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.global.constant.FixedValues;
import com.sehbeomschool.nova.domain.game.dao.AgesRepository;
import com.sehbeomschool.nova.domain.game.dao.AnalysisCommentRepository;
import com.sehbeomschool.nova.domain.game.dao.AnnualCostRepository;
import com.sehbeomschool.nova.domain.game.dao.EventRepository;
import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.dao.MyAssetsRepository;
import com.sehbeomschool.nova.domain.game.dao.OldAgeMonthlyAssetsRepository;
import com.sehbeomschool.nova.domain.game.domain.AnnualCost;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final AgesRepository agesRepository;
    private final AnalysisCommentRepository analysisCommentRepository;
    private final EventRepository eventRepository;
    private final GameRepository gameRepository;
    private final AnnualCostRepository annualCostRepository;
    private final MyAssetsRepository myAssetsRepository;
    private final OldAgeMonthlyAssetsRepository oldAgeMonthlyAssetsRepository;

    @Override
    public GameStartResponseDto createGame(GameStartRequestDto gameStartRequestDto) {
        AnnualCost annualCost = AnnualCost.createStartAnnualCost();

        // TODO: 2023-09-19 User 객체 추가 
        Game game = Game.builder()
            .myAssets(MyAssets.createStartMyAsset(gameStartRequestDto.getStartSalary(), annualCost))
            .annualCost(annualCost)
            .startSalary(gameStartRequestDto.getStartSalary())
            .gender(gameStartRequestDto.getGender())
            .currentAge(FixedValues.START_AGE.getValue())
            .build();

        game.addAgeAndSetThis(Ages.createStartAge(gameStartRequestDto.getStartSalary()));

        gameRepository.save(game);

        return GameStartResponseDto.builder().gameId(game.getId()).build();
    }
}
