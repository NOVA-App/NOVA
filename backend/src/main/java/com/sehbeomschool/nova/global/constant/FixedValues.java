package com.sehbeomschool.nova.global.constant;

public enum FixedValues {
    LIVING_COST_MIN(6_000_000D),
    MONTHLY_RENT_COST(6_000_000D),
    START_AGE(27D);


    private final Double value;

    FixedValues(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }
}
