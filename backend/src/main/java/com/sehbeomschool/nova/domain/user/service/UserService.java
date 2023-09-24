package com.sehbeomschool.nova.domain.user.service;

import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import com.sehbeomschool.nova.domain.user.dto.UserResponseDto.LoginResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Long createUser(KakaoUserInfoDto user);

    User readUser(Long userId);

    User readUserBySocialId(Long socialId);

    String updateUserProfileImg(Long userId, MultipartFile profileImg);

    void updateUserName(Long userId, String name);

    void deleteUser(Long userId);

    boolean isExistUser(Long socialId);

    LoginResponseDto login(Long userId);
}
