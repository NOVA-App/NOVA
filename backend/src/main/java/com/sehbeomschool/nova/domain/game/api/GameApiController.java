package com.sehbeomschool.nova.domain.game.api;

import static com.sehbeomschool.nova.domain.game.constant.GameResponseMessage.GAME_START_SUCCESS;
import static com.sehbeomschool.nova.domain.game.constant.GameResponseMessage.GIVE_UP_GAME_SUCCESS;
import static com.sehbeomschool.nova.domain.game.constant.GameResponseMessage.MARRY_SUCCESS;
import static com.sehbeomschool.nova.domain.game.constant.GameResponseMessage.NEXT_YEAR_UPDATE_SUCCESS;
import static com.sehbeomschool.nova.domain.game.constant.GameResponseMessage.READ_CURRENT_YEAR_SUCCESS;
import static com.sehbeomschool.nova.domain.game.constant.GameResponseMessage.READ_FIXED_COST_SUCCESS;
import static com.sehbeomschool.nova.domain.game.constant.GameResponseMessage.READ_GAME_RESULT_DETAIL_SUCCESS;
import static com.sehbeomschool.nova.domain.game.constant.GameResponseMessage.UPDATE_LIVING_COST_SUCCESS;

import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.GameStartRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.MarryRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.NextYearRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameRequestDto.UpdateLivingCostRequestDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.CurrentYearResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.FixedCostResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameResultDetailResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.GameStartResponseDto;
import com.sehbeomschool.nova.domain.game.dto.GameResponseDto.UpdateLivingCostResponseDto;
import com.sehbeomschool.nova.domain.game.service.GameService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
                GAME_START_SUCCESS.getMessage(),
                gameService.createGame(gameStartRequestDto)
            )
        );
    }

    @PutMapping("")
    public ResponseEntity<ResponseDto<?>> updateForNextYear(
        @RequestBody NextYearRequestDto nextYearRequestDto) {
        gameService.updateForNextYear(nextYearRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(NEXT_YEAR_UPDATE_SUCCESS.getMessage())
        );
    }

    @PatchMapping("/livcost")
    public ResponseEntity<ResponseDto<UpdateLivingCostResponseDto>> updateLivingCost(
        @RequestBody UpdateLivingCostRequestDto updateLivingCostRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                UPDATE_LIVING_COST_SUCCESS.getMessage(),
                gameService.updateLivingCost(updateLivingCostRequestDto)
            )
        );
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<ResponseDto<CurrentYearResponseDto>> readCurrentYear(
        @PathVariable("gameId") Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_CURRENT_YEAR_SUCCESS.getMessage(),
                gameService.readCurrentYear(gameId)
            )
        );
    }

    @GetMapping("/fixedcost/{gameId}")
    public ResponseEntity<ResponseDto<FixedCostResponseDto>> readFixedCost(
        @PathVariable("gameId") Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_FIXED_COST_SUCCESS.getMessage(),
                gameService.readFixedCost(gameId)
            )
        );
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<ResponseDto<?>> deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                GIVE_UP_GAME_SUCCESS.getMessage()
            )
        );
    }

    @GetMapping("/result/{gameId}")
    public ResponseEntity<ResponseDto<GameResultDetailResponseDto>> readGameResultDetail(
        @PathVariable("gameId") Long gameId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_GAME_RESULT_DETAIL_SUCCESS.getMessage(),
                gameService.readGameResultDetail(gameId)
            )
        );
    }

    @PostMapping("/marry")
    public ResponseEntity<ResponseDto<?>> marry(@RequestBody MarryRequestDto marryRequestDto) {
        gameService.marry(marryRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseDto.create(MARRY_SUCCESS.getMessage())
        );
    }
}
