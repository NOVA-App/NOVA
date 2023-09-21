package com.sehbeomschool.nova.domain.user.service;


import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;
import java.util.Map;

public interface WebClientService {
    public KakaoUserInfoDto getKakaoUserInfo(String code);


}
