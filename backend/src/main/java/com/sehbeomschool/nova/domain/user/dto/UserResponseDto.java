package com.sehbeomschool.nova.domain.user.dto;

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
        public UserInfoResponseDto(String name, String profileImg) {
            this.name = name;
            this.profileImg = profileImg;
        }
    }

    @Data
    public static class RefreshTokenResponseDto {

        private String accessToken;

        @Builder
        public RefreshTokenResponseDto(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @Data
    public static class FileUploadResponseDto {

        private String filePath;

        @Builder
        public FileUploadResponseDto(String filePath) {
            this.filePath = filePath;
        }
    }
}
