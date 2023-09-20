package com.sehbeomschool.nova.domain.user.service;


import java.util.Map;

public interface WebClientService {
    public Map<String, Object> getKakaoUserInfo(String code);


}
