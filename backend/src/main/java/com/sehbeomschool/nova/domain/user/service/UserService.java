package com.sehbeomschool.nova.domain.user.service;

import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.LoginResponseDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.UserInfoResponseDto;

public interface UserService {

    Long createUser(KakaoUserInfoDto user);

    UserInfoResponseDto readUser(Long userId);
    User readUserBySocialId(Long socialId);

    void updateUserProfileImg(Long userId, String profileImg);

    void updateUserName(Long userId, String name);

    void deleteUser(Long userId);

    boolean isExistUser(Long socialId);

    LoginResponseDto login(Long userId);
}
