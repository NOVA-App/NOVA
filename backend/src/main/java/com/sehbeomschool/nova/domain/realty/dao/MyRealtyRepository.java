package com.sehbeomschool.nova.domain.realty.dao;

import com.sehbeomschool.nova.domain.realty.domain.MyRealty;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRealtyRepository extends JpaRepository<MyRealty, Long> {

    List<MyRealty> findMyRealtiesByGameId(Long gameId);

    MyRealty findMyRealtyByGameIdAndRealtyId(Long gameId, Long realtyId);

    Long countByGameId(Long gameId);
}
