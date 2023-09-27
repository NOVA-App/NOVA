package com.sehbeomschool.nova.global.util;

public class TaxCalculator {

    private static int[] realtyAcquisitionTaxRate = {2, 8, 12, 12, 12, 12, 12, 12, 12};

    public static Long calRealtyAcquistionTax(Long price, Long count) {
        return price * (realtyAcquisitionTaxRate[count.intValue()]) / 100;
    }

    public static Long calRealtyTotalPrice(Long price, Long count) {
        return price * (100 + realtyAcquisitionTaxRate[count.intValue()]) / 100;
    }

}
