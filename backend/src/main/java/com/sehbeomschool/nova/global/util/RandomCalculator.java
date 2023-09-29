package com.sehbeomschool.nova.global.util;

import java.util.Random;

public class RandomCalculator {

    private static Random random = new Random();

    private static Long calInterestPercent(int limit, int bias) {
        Long val;
        do {
            val = (long) (random.nextGaussian() * bias);
        } while (val > limit || val < -limit);

        return val > 0 ? val * 2 : val;
    }

    public static Long calRealty(Long price) {
        return price * (100L + calInterestPercent(20, 10)) / 100;
    }

    public static Long calStock(Long price) {
        return price * (100L + calInterestPercent(50, 25)) / 100;
    }


    private static Long calInterestPercent(int min, int max, int bias) {
        Long val;
        do {
            val = (long) (random.nextGaussian() * bias);
        } while (val > max || val < min);

        return val;
    }

    public static Long calIrpInterest(Long price) {
        return price * (calInterestPercent(-1, 10, 5)) / 100;
    }
}
