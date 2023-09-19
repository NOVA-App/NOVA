package com.sehbeomschool.nova.domain.game.service;

import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;

public interface GameService {

    GameStartResponseDto createGame(GameStartRequestDto gameStartRequestDto);
}
