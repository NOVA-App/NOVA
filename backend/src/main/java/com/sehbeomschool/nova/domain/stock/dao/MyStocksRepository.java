package com.sehbeomschool.nova.domain.stock.dao;

import com.sehbeomschool.nova.domain.stock.domain.MyStocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStocksRepository extends JpaRepository<MyStocks, Long> {

}
