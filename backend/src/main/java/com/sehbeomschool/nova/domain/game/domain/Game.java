package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.domain.game.constant.EventType;
import com.sehbeomschool.nova.domain.game.constant.Gender;
import com.sehbeomschool.nova.domain.realty.domain.MyRealty;
import com.sehbeomschool.nova.domain.stock.domain.MyStocks;
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

    private Double assetGrowthRate;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ages> ages = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyStocks> myStocks = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyRealty> myRealties = new ArrayList<>();

    @Builder
    public Game(Long id, User user, AnalysisComment analysisComment, MyAssets myAssets,
        OldAgeMonthlyAssets oldAgeMonthlyAssets, AnnualAsset annualAsset, Integer startSalary,
        Gender gender, Long resultAssets, Integer currentAge, Double assetGrowthRate) {
        this.id = id;
        this.user = user;
        this.analysisComment = analysisComment;
        this.myAssets = myAssets;
        this.oldAgeMonthlyAssets = oldAgeMonthlyAssets;
        this.annualAsset = annualAsset;
        this.startSalary = startSalary;
        this.gender = gender;
        this.resultAssets = resultAssets;
        this.currentAge = currentAge;
        this.assetGrowthRate = assetGrowthRate;
    }

    public void addAgeAndSetThis(Ages age) {
        this.ages.add(age);
        age.setGame(this);
    }

    public void increaseCurrentAge() {
        this.currentAge += 1;
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

    public void addMyStockAndSetThis(MyStocks myStock) {
        this.myStocks.add(myStock);
        myStock.setGame(this);
    }

    public void addMyRealtyAndSetThis(MyRealty myRealty) {
        this.myRealties.add(myRealty);
        myRealty.setGame(this);
    }

    public void setOldAgeMonthlyAssets(OldAgeMonthlyAssets oldAgeMonthlyAssets) {
        this.oldAgeMonthlyAssets = oldAgeMonthlyAssets;
    }

    public void setAnalysisComment(AnalysisComment analysisComment) {
        this.analysisComment = analysisComment;
    }

    public void setResultAssets() {
        this.resultAssets = this.myAssets.getTotalAsset();
    }

    public void setAssetGrowthRate() {
        int startAge = FixedValues.START_AGE.getValue().intValue();
        int endAge = FixedValues.END_AGE.getValue().intValue();
        Long defaultAsset = 0L;
        Long nextSalary = 0L;

        for (int i = startAge; i < endAge; i++) {
            nextSalary = this.startSalary.longValue();
            for (int j = startAge; j < i; j++) {
                nextSalary = (long) (nextSalary * FixedValues.SALARY_INCREASE_RATE.getValue());
            }
            defaultAsset += nextSalary;
        }

        Long minFixedCost = FixedValues.LIVING_COST_MIN.getValue().longValue()
            + FixedValues.MONTHLY_RENT_COST.getValue().longValue();
        defaultAsset -= minFixedCost * (endAge - startAge);

        this.assetGrowthRate = (double) this.resultAssets / (double) defaultAsset;
    }

    public Long calStockInvestAmount() {
        Long investAmount = 0L;

        for (MyStocks ms : this.myStocks) {
            investAmount += ms.getInvestAmount();
        }

        return investAmount;
    }

    public Long calRealtyInvestAmount() {
        Long investAmount = 0L;

        for (MyRealty mr : this.myRealties) {
            investAmount += mr.getInvestAmount();
        }

        return investAmount;
    }

    private void addChildCost() {
        this.annualAsset.addChildCost();
    }

    private void payMarriageCost() {
        this.annualAsset.useUsableAsset(FixedValues.MARRIAGE_COST.getValue().longValue());
    }
}
