package com.sehbeomschool.nova.domain.game.service;

import com.sehbeomschool.nova.domain.game.constant.GameStatus;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.MarryRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.NextYearRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.UpdateLivingCostRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.CurrentYearResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.FixedCostResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameResultDetailResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.InProgressGameResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.MyResultsListResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.RankingListResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.UpdateLivingCostResponseDto;

public interface GameService {

    GameStartResponseDto createGame(GameStartRequestDto gameStartRequestDto, Long userId);

    GameStatus updateForNextYear(NextYearRequestDto nextYearRequestDto);

    CurrentYearResponseDto readCurrentYear(Long gameId);

    UpdateLivingCostResponseDto updateLivingCost(
        UpdateLivingCostRequestDto updateLivingCostRequestDto);

    FixedCostResponseDto readFixedCost(Long gameId);

    void deleteGame(Long gameId);

    GameResultDetailResponseDto readGameResultDetail(Long gameId);

    MyResultsListResponseDto readAllMyGames(Long userId);

    RankingListResponseDto readRankingList();

    void marry(MarryRequestDto marryRequestDto);

    InProgressGameResponseDto readInProgressGame(Long userId);
}
