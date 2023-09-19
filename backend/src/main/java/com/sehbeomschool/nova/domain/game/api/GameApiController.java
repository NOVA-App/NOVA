package com.sehbeomschool.nova.domain.game.api;

import com.sehbeomschool.nova.domain.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameApiController {

    private final GameService gameService;
}
