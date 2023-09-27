package com.sehbeomschool.nova.domain.realty.service;

import com.sehbeomschool.nova.domain.realty.dto.RealtyRequestDto.TradeRealtyRequestDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyDetailResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadMyRealtyResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadRealtyDetailResponseDto;
import com.sehbeomschool.nova.domain.realty.dto.RealtyResponseDto.ReadRealtyResponseDto;
import java.util.List;

public interface RealtyService {

    ReadMyRealtyResponseDto readMyRealty(Long gameId);

    ReadMyRealtyDetailResponseDto readMyRealtyDetail(Long gameId, Long realtyId);

    List<ReadRealtyResponseDto> readRealtyList(Long gameId);

    ReadRealtyDetailResponseDto readRealtyDetail(Long gameId, Long realtyId);

    void buyRealty(TradeRealtyRequestDto tradeRealtyRequestDto);

    void sellRealty(Long gameId, Long realtyId);
}
