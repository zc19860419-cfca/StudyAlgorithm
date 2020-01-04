package com.sadk.algorithm.karatsuba;

import org.junit.Test;

import java.math.BigInteger;

/**
 * @author zhangchong
 * @Create 2019/5/16 11:20
 * @CodeReviewer
 * @Description
 * @since v3.0.0.0
 */
public class KaratsubaTest {

    @Test
    public void multiply() {
        final BigInteger bigInteger = new BigInteger("15208418271");
        System.out.println(bigInteger.toString());
        System.out.println(bigInteger.bitCount());
        System.out.println(bigInteger.bitLength());
        //1110001010011111100000101111011111

    }
}