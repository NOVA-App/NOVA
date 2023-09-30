package com.sehbeomschool.nova.domain.game.dao;

import com.sehbeomschool.nova.domain.game.domain.Game;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends JpaRepository<Game, Long> {

    @EntityGraph(attributePaths = {"myAssets", "annualAsset"})
    Optional<Game> findById(Long gameId);

    @EntityGraph(attributePaths = {"myAssets", "annualAsset"})
    @Query("SELECT g FROM Game g WHERE g.user.id = :userId AND g.currentAge = 60")
    List<Game> findFinishedGameByUserId(@Param("userId") Long userId);

    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT g FROM Game g WHERE g.currentAge = 60")
    List<Game> findRankList(Pageable pageable);
}
