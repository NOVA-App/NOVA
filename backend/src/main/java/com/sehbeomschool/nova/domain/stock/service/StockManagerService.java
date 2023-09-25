package com.sehbeomschool.nova.domain.stock.service;

import com.sehbeomschool.nova.domain.game.domain.Ages;

public interface StockManagerService {

    public void createStocksInfoByGameStart(Ages age);

    public void createStocksInfoByNextYear(Ages prevAge, Ages nextAge);

}
