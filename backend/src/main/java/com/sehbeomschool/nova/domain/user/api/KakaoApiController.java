package com.sehbeomschool.nova.domain.user.api;

import com.sehbeomschool.nova.domain.user.constant.UserResponseMessage;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.LoginResponseDto;
import com.sehbeomschool.nova.domain.user.service.UserService;
import com.sehbeomschool.nova.domain.user.service.WebClientService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import com.sehbeomschool.nova.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth/kakao")
@RequiredArgsConstructor
public class KakaoApiController {

    private final WebClientService webClientService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/callback")
    public ResponseEntity<ResponseDto<LoginResponseDto>> kakaoLogin(@RequestParam("code") String code) {
        KakaoUserInfoDto kakaoUserInfo = webClientService.getKakaoUserInfo(code);

        if (!userService.isExistUser(kakaoUserInfo.getId())) {
            userService.createUser(kakaoUserInfo);
        }

        User user = userService.readUserBySocialId(kakaoUserInfo.getId());

        String accessToken = jwtUtil.createJwtToken(user.getId());
        String refreshToken = jwtUtil.createRefreshToken(user.getId());

        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body(ResponseDto.create(UserResponseMessage.LOGIN_SUCCESS.getMessage(),
                LoginResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken)
                    .build()));
    }

//    @GetMapping("/callback")
//    public ResponseEntity<ResponseDto> kakaoLoginError(@RequestParam(name = "error") String error) {
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(ResponseDto.create(UserResponseMessage.LOGIN_FAIL.getMessage() + " " + error));
//    }
}
