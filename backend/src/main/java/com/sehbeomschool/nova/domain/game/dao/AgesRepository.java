package com.sehbeomschool.nova.domain.game.dao;

import com.sehbeomschool.nova.domain.game.domain.Ages;
import com.sehbeomschool.nova.domain.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgesRepository extends JpaRepository<Ages, Long> {

    @Query("SELECT a FROM Ages a WHERE a.game = :game AND a.age = (SELECT MAX(ma.age) FROM Ages ma WHERE ma.game = :game)")
    Ages findCurrentAge(@Param("game") Game game);
}
