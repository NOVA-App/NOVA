package com.sehbeomschool.nova.domain.user.api;


import com.sehbeomschool.nova.domain.user.domain.CustomUserDetails;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.UserInfoResponseDto;
import com.sehbeomschool.nova.domain.user.service.UserService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import com.sehbeomschool.nova.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<ResponseDto<UserInfoResponseDto>> getUserInfo(
        @AuthenticationPrincipal CustomUserDetails authentication) {
        User user = authentication.getUser();
        return ResponseEntity.status(
            HttpStatus.OK).body(ResponseDto.create("",
            UserInfoResponseDto.builder().name(user.getName()).profileImg(user.getProfileImg())
                .build()));
    }

    @GetMapping("/token")
    public ResponseEntity<String> token() {
        KakaoUserInfoDto test1 = KakaoUserInfoDto.builder().id(123L).name("test1")
            .profileImg("").build();
        userService.createUser(test1);
        String jwtToken = jwtUtil.createJwtToken(1L);
        return ResponseEntity.ok(jwtToken);
    }

}
