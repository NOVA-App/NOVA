package com.sehbeomschool.nova.domain.game.dao;

import com.sehbeomschool.nova.domain.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
