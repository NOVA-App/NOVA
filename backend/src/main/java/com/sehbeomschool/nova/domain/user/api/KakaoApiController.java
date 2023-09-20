package com.sehbeomschool.nova.domain.user.api;

import com.sehbeomschool.nova.domain.user.constant.UserResponseMessage;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.LoginResponseDto;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth/kakao")
public class KakaoApiController {

    @GetMapping("/callback")
    public ResponseEntity<ResponseDto<LoginResponseDto>> kakaoLogin(@RequestParam String code) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body(ResponseDto.create(UserResponseMessage.LOGIN_SUCCESS.getMessage(),
                LoginResponseDto.builder().accessToken("").refreshToken("").build()));
    }
}
