package com.sehbeomschool.nova.domain.stock.dao;

import com.sehbeomschool.nova.domain.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
