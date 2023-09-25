package com.sehbeomschool.nova.global.util;

import java.util.Random;

public class RandomCalculator {

    Random random = new Random();

    private Long calPercent(int limit, int bias) {
        Long val;
        do {
            val = (long) (random.nextGaussian() * bias);
        } while (val > limit || val < -limit);

        return val > 0 ? val * 2 : val;
    }

    public Long calRealty(Long price) {
        return price * (100L + calPercent(20, 10)) / 100;
    }

    public Long calStock(Long price) {
        return price * (100L + calPercent(50, 25)) / 100;
    }
}
