package com.sehbeomschool.nova.domain.stock.service;

import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.domain.game.domain.Game;
import java.util.List;

public interface StockManagerService {

    public void createStocksInfoByGameStart(Ages age);

    public void createStocksInfoByNextYear(Ages prevAge, Ages nextAge);

    public void deleteStocksInfo(List<Ages> ages);

    public Long calStockAssetForNextYear(Game game);
}
