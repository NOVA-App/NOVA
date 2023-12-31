package com.sehbeomschool.nova.domain.news.api;

import static com.sehbeomschool.nova.domain.news.constant.NewsResponseMessage.READ_NEWS;

import com.sehbeomschool.nova.domain.news.dto.NewsResponseDto.ReadNewsResponseDto;
import com.sehbeomschool.nova.domain.news.service.NewsService;
import com.sehbeomschool.nova.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsApiController {

    private final NewsService newsService;

    @GetMapping("/{gameId}")
    public ResponseEntity<ResponseDto<ReadNewsResponseDto>> readNews(@PathVariable Long gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(
                READ_NEWS.getMessage(),
                newsService.readNews(gameId)
            )
        );
    }
}
