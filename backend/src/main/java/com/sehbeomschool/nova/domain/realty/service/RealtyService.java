package com.sehbeomschool.nova.domain.realty.service;

import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyResponseDto;

public interface RealtyService {

    ReadMyRealtyResponseDto readMyRealty(Long gameId);
}
