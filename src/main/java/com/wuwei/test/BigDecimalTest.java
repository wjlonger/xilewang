package com.wuwei.test;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal ratio = BigDecimal.valueOf(0L);
        ratio = ratio.add(BigDecimal.valueOf(50L));
        BigDecimal money  = BigDecimal.valueOf(0.05);
        System.out.println(money.multiply(ratio).divide(BigDecimal.valueOf(100L)));
    }

}
