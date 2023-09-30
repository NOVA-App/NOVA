package com.sehbeomschool.nova.domain.user.api;

import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.LOGIN_SUCCESS;

import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.TokenResponseDto;
import com.sehbeomschool.nova.domain.user.service.UserService;
import com.sehbeomschool.nova.domain.user.service.WebClientService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import com.sehbeomschool.nova.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth/kakao")
@RequiredArgsConstructor
@Slf4j
public class KakaoApiController {

    private final WebClientService webClientService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/callback")
    public void kakaoLogin(@RequestParam("code") String code) {
        log.info("kakao callback");
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDto<TokenResponseDto>> login(
        @RequestParam("code") String code) {

        KakaoUserInfoDto kakaoUserInfo = webClientService.getKakaoUserInfo(code);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body(ResponseDto.create(LOGIN_SUCCESS.getMessage(),
                userService.kakaoLogin(kakaoUserInfo)));
    }


}
