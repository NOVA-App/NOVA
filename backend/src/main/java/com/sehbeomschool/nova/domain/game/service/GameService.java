package com.sehbeomschool.nova.domain.game.service;

import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.MarryRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.NextYearRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.UpdateLivingCostRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.CurrentYearResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.FixedCostResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.UpdateLivingCostResponseDto;

public interface GameService {

    GameStartResponseDto createGame(GameStartRequestDto gameStartRequestDto);

    void updateForNextYear(NextYearRequestDto nextYearRequestDto);

    CurrentYearResponseDto readCurrentYear(Long gameId);

    UpdateLivingCostResponseDto updateLivingCost(
        UpdateLivingCostRequestDto updateLivingCostRequestDto);

    FixedCostResponseDto readFixedCost(Long gameId);

    void deleteGame(Long gameId);

    void marry(MarryRequestDto marryRequestDto);
}
