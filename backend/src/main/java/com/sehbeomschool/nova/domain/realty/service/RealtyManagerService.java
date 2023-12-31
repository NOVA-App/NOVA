package com.sehbeomschool.nova.domain.realty.service;

import com.sehbeomschool.nova.domain.game.domain.Game;

public interface RealtyManagerService {

    public void createRealtyInfoByGameStart(Game game);

    public void updateRealtyInfoByNextYear(Long gameId);

    public void updateMyRealtyByNextYear(Game game);

    public void deleteRealtyInfo(Long gameId);

    public Long calRealtyAssetForNextYear(Game game);
}
