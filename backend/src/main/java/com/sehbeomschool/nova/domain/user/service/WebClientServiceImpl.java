package com.sehbeomschool.nova.domain.user.service;

import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientServiceImpl implements WebClientService {

    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.url.token.host}")
    private String tokenHost;

    @Value("${kakao.url.token.path}")
    private String tokenUrl;

    @Value("${kakao.url.profile.host}")
    private String infoHost;

    @Value("${kakao.url.profile.path}")
    private String infoUrl;

    @Value("${kakao.redirect}")
    private String redirectUrl;


    @Override
    public KakaoUserInfoDto getKakaoUserInfo(String code) {
        WebClient webClient =
            WebClient
                .builder()
                .build();

        Map result = webClient
            .post()
            .uri(uriBuilder ->
                uriBuilder
                    .scheme("https") // 스키마 설정 (https)
                    .host(tokenHost)
                    .path(tokenUrl)
                    .queryParam("code", code)
                    .queryParam("grant_type", "authorization_code")
                    .queryParam("client_id", clientId)
                    .queryParam("redirect_uri", redirectUrl)
                    .build())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

        String accessToken = String.valueOf(result.get("access_token"));
        String refreshToken = String.valueOf(result.get("refresh_token"));

        result = webClient
            .get()
            .uri(uriBuilder ->
                uriBuilder
                    .scheme("https")
                    .host(infoHost)
                    .path(infoUrl)
                    .build())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

        return new KakaoUserInfoDto(result);
    }
}
