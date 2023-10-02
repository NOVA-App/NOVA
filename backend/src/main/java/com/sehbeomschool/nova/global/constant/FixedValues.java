package com.sehbeomschool.nova.global.constant;

public enum FixedValues {
    LIVING_COST_MIN(6_000_000D),
    MONTHLY_RENT_COST(6_000_000D),
    START_AGE(27D),
    END_AGE(60D),
    NUM_OF_MONTHS_OF_OLD_AGE(480D),
    CHILD_ALLOWANCE(500_000D),
    SALARY_INCREASE_RATE(1.05),
    MARRIAGE_COST(20_000_000D),
    CHILD_COST(8_400_000D),
    INSTALLMENT_TAX_PERCENTAGE(15.4),
    NATIONAL_PENSION_PERCENTAGE(0.045),
    LOAN_INTEREST_PERCENTAGE(0.07);

    private final Double value;

    FixedValues(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }
}
