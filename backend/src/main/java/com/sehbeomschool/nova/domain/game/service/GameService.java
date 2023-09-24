package com.sehbeomschool.nova.domain.game.service;

import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.MarryRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.CurrentYearResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.FixedCostResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;

public interface GameService {

    GameStartResponseDto createGame(GameStartRequestDto gameStartRequestDto);

    CurrentYearResponseDto readCurrentYear(Long gameId);

    FixedCostResponseDto readFixedCost(Long gameId);

    void marry(MarryRequestDto marryRequestDto);
}
