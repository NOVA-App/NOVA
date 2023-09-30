package com.sehbeomschool.nova.domain.user.service;


import com.sehbeomschool.nova.domain.user.dto.KakaoUserInfoDto;

public interface WebClientService {

    KakaoUserInfoDto getKakaoUserInfo(String code);


}
