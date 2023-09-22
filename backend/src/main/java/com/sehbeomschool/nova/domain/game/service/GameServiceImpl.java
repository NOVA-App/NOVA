package com.sehbeomschool.nova.domain.game.service;

import static com.sehbeomschool.nova.domain.game.constant.GameExceptionMessage.GAME_NOT_FOUND;

import com.sehbeomschool.nova.domain.game.constant.EventType;
import com.sehbeomschool.nova.domain.game.dao.AgesRepository;
import com.sehbeomschool.nova.domain.game.dao.AnalysisCommentRepository;
import com.sehbeomschool.nova.domain.game.dao.AnnualCostRepository;
import com.sehbeomschool.nova.domain.game.dao.EventRepository;
import com.sehbeomschool.nova.domain.game.dao.GameRepository;
import com.sehbeomschool.nova.domain.game.dao.MyAssetsRepository;
import com.sehbeomschool.nova.domain.game.dao.OldAgeMonthlyAssetsRepository;
import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.domain.game.domain.AnnualCost;
import com.sehbeomschool.nova.domain.game.domain.Event;
import com.sehbeomschool.nova.domain.game.domain.Game;
import com.sehbeomschool.nova.domain.game.domain.MyAssets;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.MarryRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;
import com.sehbeomschool.nova.domain.game.exception.GameNotFoundException;
import com.sehbeomschool.nova.global.constant.FixedValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    private final AgesRepository agesRepository;
    private final AnalysisCommentRepository analysisCommentRepository;
    private final EventRepository eventRepository;
    private final GameRepository gameRepository;
    private final AnnualCostRepository annualCostRepository;
    private final MyAssetsRepository myAssetsRepository;
    private final OldAgeMonthlyAssetsRepository oldAgeMonthlyAssetsRepository;

    @Override
    @Transactional
    public GameStartResponseDto createGame(GameStartRequestDto gameStartRequestDto) {
        AnnualCost annualCost = AnnualCost.createStartAnnualCost();

        // TODO: 2023-09-19 User 객체 추가 
        Game game = Game.builder()
            .myAssets(MyAssets.createStartMyAsset(gameStartRequestDto.getStartSalary(), annualCost))
            .annualCost(annualCost)
            .startSalary(gameStartRequestDto.getStartSalary())
            .gender(gameStartRequestDto.getGender())
            .currentAge(FixedValues.START_AGE.getValue().intValue())
            .build();

        Ages startAge = Ages.createStartAge(gameStartRequestDto.getStartSalary());

        game.addAgeAndSetThis(startAge);

        gameRepository.save(game);

        return GameStartResponseDto.builder().gameId(game.getId()).build();
    }

    @Override
    @Transactional
    public void marry(MarryRequestDto marryRequestDto) {
        Game game = gameRepository.findById(marryRequestDto.getGameId())
            .orElseThrow(() -> new GameNotFoundException(GAME_NOT_FOUND.getMessage()));

        game.addEventAndSetThis(Event.builder()
            .age(agesRepository.findCurrentAge(game))
            .eventType(EventType.MARRIAGE)
            .build());
    }
}
