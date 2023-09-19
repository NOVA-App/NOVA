package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.domain.game.constant.Gender;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANALYSIS_COMMENT_ID")
    private AnalysisComment analysisComment;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MY_ASSET_ID")
    private MyAssets myAssets;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "OLD_AGE_MONTHLY_ASSET_ID")
    private OldAgeMonthlyAssets oldAgeMonthlyAssets;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MONTHLY_COST_ID")
    private MonthlyCost monthlyCost;

    private Integer startSalary;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Long resultAssets;

    private Integer currentAge;

    @Builder
    public Game(Long id, AnalysisComment analysisComment, MyAssets myAssets,
        OldAgeMonthlyAssets oldAgeMonthlyAssets, MonthlyCost monthlyCost, Integer startSalary,
        Gender gender, Long resultAssets, Integer currentAge) {
        this.id = id;
        this.analysisComment = analysisComment;
        this.myAssets = myAssets;
        this.oldAgeMonthlyAssets = oldAgeMonthlyAssets;
        this.monthlyCost = monthlyCost;
        this.startSalary = startSalary;
        this.gender = gender;
        this.resultAssets = resultAssets;
        this.currentAge = currentAge;
    }
}
