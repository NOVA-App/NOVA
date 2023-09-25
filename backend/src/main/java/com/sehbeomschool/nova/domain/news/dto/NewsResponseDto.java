package com.sehbeomschool.nova.domain.news.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class NewsResponseDto {

    @Getter
    public static class ReadNewsResponseDto {

        private List<String> news;

        @Builder
        public ReadNewsResponseDto(List<String> news) {
            this.news = news;
        }
    }
}
