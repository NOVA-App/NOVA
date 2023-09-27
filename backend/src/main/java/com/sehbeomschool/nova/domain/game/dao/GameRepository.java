package com.sehbeomschool.nova.domain.game.dao;

import com.sehbeomschool.nova.domain.game.domain.Game;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    @EntityGraph(attributePaths = {"myAssets", "annualAsset"})
    Optional<Game> findById(Long gameId);
}
