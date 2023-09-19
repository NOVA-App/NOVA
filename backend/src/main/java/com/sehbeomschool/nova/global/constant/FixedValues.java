package com.sehbeomschool.nova.global.constant;

public enum FixedValues {
    LIVING_COST_MIN(6_000_000),
    MONTHLY_RENT_COST(6_000_000),
    START_AGE(27);


    private final int value;

    FixedValues(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
