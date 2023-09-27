package com.sehbeomschool.nova.global.constant;

public enum FixedValues {
    LIVING_COST_MIN(6_000_000D),
    MONTHLY_RENT_COST(6_000_000D),
    START_AGE(27D),
    SALARY_INCREASE_RATE(1.05),
    MARRIAGE_COST(20_000_000D),
    CHILD_COST(8_400_000D);


    private final Double value;

    FixedValues(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }
}
