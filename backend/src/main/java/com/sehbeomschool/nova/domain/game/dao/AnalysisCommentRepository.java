package com.sehbeomschool.nova.domain.game.dao;

import com.sehbeomschool.nova.domain.game.domain.AnalysisComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnalysisCommentRepository extends JpaRepository<AnalysisComment, Long> {

    @Query("SELECT a FROM AnalysisComment a WHERE :totalMonthlyAsset BETWEEN a.minAsset AND a.maxAsset")
    AnalysisComment findBetweenMinAndMaxAsset(
        @Param("totalMonthlyAsset") Integer totalMonthlyAsset);
}
