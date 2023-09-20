package com.sehbeomschool.nova.domain.game.api;

import com.sehbeomschool.nova.domain.game.constant.GameResponseMessage;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;
import com.sehbeomschool.nova.domain.game.service.GameService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameApiController {

    private final GameService gameService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<GameStartResponseDto>> createGame(@RequestBody
    GameStartRequestDto gameStartRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseDto.create(
                GameResponseMessage.GAME_START_SUCCESS.getMessage(),
                gameService.createGame(gameStartRequestDto)
            )
        );
    }
}
