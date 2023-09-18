package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAME_ID")
    private Long id;

    // TODO: User Entity ManyToOne 연결

    // TODO: Gender Enum 추가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANALYSIS_COMMENT_ID")
    private AnalysisComment analysisComment;

    private Integer startSalary;
    private Long resultAssets;
    private Integer currentAge;

    @Builder
    public Game(Long id, AnalysisComment analysisComment, Integer startSalary, Long resultAssets,
        Integer currentAge) {
        this.id = id;
        this.analysisComment = analysisComment;
        this.startSalary = startSalary;
        this.resultAssets = resultAssets;
        this.currentAge = currentAge;
    }
}
