package com.sehbeomschool.nova.domain.news.dto;

import com.sehbeomschool.nova.domain.news.domain.News;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class NewsResponseDto {

    @Getter
    public static class readNewsResponseDto {
        private List<News> news;

        @Builder
        public readNewsResponseDto(List<News> news) {
            this.news = news;
        }
    }
}
