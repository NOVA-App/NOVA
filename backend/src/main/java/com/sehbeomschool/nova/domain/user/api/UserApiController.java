package com.sehbeomschool.nova.domain.user.api;


import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.CREATE_ACCESS_TOKEN;
import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.SUCCESS_GET_USER_INFO;
import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.SUCCES_NAME_UPDATE;
import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.SUCCES_PROFILE_UPDATE;
import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.SUCCES_USER_DELETE;

import com.sehbeomschool.nova.domain.user.domain.CustomUserDetails;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.FileUploadResponseDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.RefreshTokenResponseDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.UserInfoResponseDto;
import com.sehbeomschool.nova.domain.user.service.UserService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import com.sehbeomschool.nova.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<ResponseDto<UserInfoResponseDto>> getUserInfo(
        @AuthenticationPrincipal Long userId) {

        return ResponseEntity.status(
            HttpStatus.OK).body(ResponseDto.create(SUCCESS_GET_USER_INFO.getMessage(),
            userService.readUser(userId)));
    }

    @GetMapping("/token")
    public ResponseEntity<String> token() {
        KakaoUserInfoDto test1 = KakaoUserInfoDto.builder().id(123L).name("test1")
            .profileImg("").build();
        userService.createUser(test1);
        String jwtToken = jwtUtil.createJwtToken(1L);
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<ResponseDto<RefreshTokenResponseDto>> getUserInfo(
        @RequestBody String refreshToken) {
        String accessToken = jwtUtil.reCreateJwtToken(refreshToken);
        return ResponseEntity.status(
            HttpStatus.OK).body(ResponseDto.create(CREATE_ACCESS_TOKEN.getMessage(),
            RefreshTokenResponseDto.builder().accessToken(accessToken)
                .build()));
    }

    @PatchMapping("/profileimg")
    public ResponseEntity<ResponseDto<FileUploadResponseDto>> modifyProfileImg
        (@AuthenticationPrincipal Long userId,
            @RequestParam MultipartFile profile) {

        String filePath = userService.updateUserProfileImg(userId, profile);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SUCCES_PROFILE_UPDATE.getMessage(),
                FileUploadResponseDto.builder().filePath(filePath).build()));
    }

    @PatchMapping("/name")
    public ResponseEntity<ResponseDto<FileUploadResponseDto>> modifyName(
        @AuthenticationPrincipal Long userId, @RequestBody String name) {

        userService.updateUserName(userId, name);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SUCCES_NAME_UPDATE.getMessage(), null));
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<FileUploadResponseDto>> modifyName(
        @AuthenticationPrincipal Long userId) {

        userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SUCCES_USER_DELETE.getMessage()));
    }

}
