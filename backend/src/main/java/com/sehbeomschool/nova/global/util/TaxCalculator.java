package com.sehbeomschool.nova.global.util;

public class TaxCalculator {

    static int[] realtyAcquisitionTaxRate = {2, 8, 12, 12, 12, 12, 12, 12};

    public Long calRealtyAcquistionTax(Long price, Long count) {
        return price * (realtyAcquisitionTaxRate[count.intValue()]) / 100;
    }

}
