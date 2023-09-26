package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.domain.game.constant.EventType;
import com.sehbeomschool.nova.domain.game.constant.Gender;
import com.sehbeomschool.nova.domain.user.domain.User;
import com.sehbeomschool.nova.global.constant.FixedValues;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

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
    @JoinColumn(name = "ANNUAL_ASSET_ID")
    private AnnualAsset annualAsset;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    private Integer startSalary;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Long resultAssets;

    private Integer currentAge;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ages> ages = new ArrayList<>();

    @Builder
    public Game(Long id, AnalysisComment analysisComment, MyAssets myAssets,
        OldAgeMonthlyAssets oldAgeMonthlyAssets, AnnualAsset annualAsset, Integer startSalary,
        Gender gender, Long resultAssets, Integer currentAge) {
        this.id = id;
        this.analysisComment = analysisComment;
        this.myAssets = myAssets;
        this.oldAgeMonthlyAssets = oldAgeMonthlyAssets;
        this.annualAsset = annualAsset;
        this.startSalary = startSalary;
        this.gender = gender;
        this.resultAssets = resultAssets;
        this.currentAge = currentAge;
    }

    public void addAgeAndSetThis(Ages age) {
        this.ages.add(age);
        age.setGame(this);
    }

    public void setCurrentAge(Integer age) {
        this.currentAge = age;
    }

    public void addEventAndSetThis(Event event) {
        this.events.add(event);
        event.setGame(this);

        if (event.getEventType() == EventType.MARRIAGE) {
            payMarriageCost();
            return;
        }

        if (event.getEventType() == EventType.CHILD_BIRTH) {
            addChildCost();
        }
    }

    private void addChildCost() {
        this.annualAsset.addChildCost();
    }

    private void payMarriageCost() {
        this.annualAsset.useUsableAsset(FixedValues.MARRIAGE_COST.getValue().longValue());
    }
}
