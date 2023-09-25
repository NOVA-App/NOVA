package com.sehbeomschool.nova.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserResponseDto {

    @Builder
    public static class LoginResponseDto{
        private String accessToken;
        private String refreshToken;
    }

    @Builder
    public static class UserInfoResponseDto{
        private String name;
        private String profileImg;
    }
}
