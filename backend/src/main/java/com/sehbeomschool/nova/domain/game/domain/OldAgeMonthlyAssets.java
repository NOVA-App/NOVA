package com.sehbeomschool.nova.domain.game.domain;

import com.sehbeomschool.nova.domain.game.constant.EventType;
import com.sehbeomschool.nova.domain.realty.domain.MyRealty;
import com.sehbeomschool.nova.global.constant.FixedValues;
import com.sehbeomschool.nova.global.entity.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OldAgeMonthlyAssets extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OLD_AGE_MONTHLY_ASSET_ID")
    private Long id;

    private Integer monthlyAmount;

    private Integer childAllowance;

    private Integer realtyRentIncome;

    private Integer nationalPension;

    private Integer totalMonthlyAsset;

    @Builder
    public OldAgeMonthlyAssets(Game game) {
        setMonthlyAmount(game.getMyAssets().getTotalAssetsExceptRealty());
        setChildAllowance(game.getEvents());
        setRealtyRentIncome(game.getMyRealties());
        setNationalPension(game.getStartSalary());
        setTotalMonthlyAsset();
    }

    private void setMonthlyAmount(Long totalAssetExceptRealty) {
        this.monthlyAmount = (int) (totalAssetExceptRealty
            / FixedValues.NUM_OF_MONTHS_OF_OLD_AGE.getValue().longValue());
    }

    private void setChildAllowance(List<Event> events) {
        this.childAllowance = 0;
        for (Event e : events) {
            if (e.getEventType() == EventType.CHILD_BIRTH) {
                this.childAllowance += FixedValues.CHILD_ALLOWANCE.getValue().intValue();
            }
        }
    }

    private void setRealtyRentIncome(List<MyRealty> myRealties) {
        this.realtyRentIncome = 0;
        int year = 12;
        for (MyRealty mr : myRealties) {
            this.realtyRentIncome += mr.getRentIncome().intValue() / year;
        }
    }

    private void setNationalPension(Integer startSalary) {
        this.nationalPension = 0;

        int startAge = FixedValues.START_AGE.getValue().intValue();
        int endAge = FixedValues.END_AGE.getValue().intValue();

        Long nextSalary = startSalary.longValue();
        for (int j = startAge; j < endAge; j++) {
            this.nationalPension += (int) (nextSalary
                * FixedValues.NATIONAL_PENSION_PERCENTAGE.getValue() * 2);
            nextSalary = (long) (nextSalary * FixedValues.SALARY_INCREASE_RATE.getValue());
        }

        this.nationalPension = (int) (this.nationalPension
            / FixedValues.NUM_OF_MONTHS_OF_OLD_AGE.getValue());
    }

    private void setTotalMonthlyAsset() {
        this.totalMonthlyAsset = 0;
        this.totalMonthlyAsset += this.monthlyAmount;
        this.totalMonthlyAsset += this.childAllowance;
        this.totalMonthlyAsset += this.realtyRentIncome;
        this.totalMonthlyAsset += this.nationalPension;
    }
}
