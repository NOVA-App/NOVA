package com.sehbeomschool.nova.domain.user.dto;

import com.sehbeomschool.nova.domain.user.domain.User;
import lombok.Builder;
import lombok.Data;

public class UserResponseDto {

    @Data
    public static class LoginResponseDto {

        private String accessToken;
        private String refreshToken;

        @Builder
        public LoginResponseDto(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Data
    public static class UserInfoResponseDto {

        private String name;
        private String profileImg;

        @Builder
        public UserInfoResponseDto(User user) {
            this.name = user.getName();
            this.profileImg = user.getProfileImg();
        }
    }
}
