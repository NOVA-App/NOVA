package com.sehbeomschool.nova.domain.user.api;


import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.CREATE_ACCESS_TOKEN;
import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.SUCCESS_GET_USER_INFO;
import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.SUCCES_NAME_UPDATE;
import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.SUCCES_PROFILE_UPDATE;
import static com.sehbeomschool.nova.domain.user.constant.UserResponseMessage.SUCCES_USER_DELETE;

import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.FileUploadResponseDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.TokenResponseDto;
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

    @GetMapping("/refreshtoken")
    public ResponseEntity<ResponseDto<TokenResponseDto>> getUserInfo(
        @RequestBody String refreshToken) {

        return ResponseEntity.status(
            HttpStatus.OK).body(ResponseDto.create(CREATE_ACCESS_TOKEN.getMessage(),
            jwtUtil.reCreateJwtToken(refreshToken)));
    }

    @PatchMapping("/profileimg")
    public ResponseEntity<ResponseDto<FileUploadResponseDto>> modifyProfileImg
        (@AuthenticationPrincipal Long userId, @RequestParam MultipartFile profile) {

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SUCCES_PROFILE_UPDATE.getMessage(),
                userService.updateUserProfileImg(userId, profile)));
    }

    @PatchMapping("/name")
    public ResponseEntity<ResponseDto<FileUploadResponseDto>> modifyName(
        @AuthenticationPrincipal Long userId, @RequestBody String name) {

        userService.updateUserName(userId, name);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SUCCES_NAME_UPDATE.getMessage()));
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<FileUploadResponseDto>> modifyName(
        @AuthenticationPrincipal Long userId) {

        userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SUCCES_USER_DELETE.getMessage()));
    }

}
