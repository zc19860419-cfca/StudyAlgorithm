package com.sadk.algorithm.demo;

import com.sadk.algorithm.arabicNumerals.ArabicNumeralsConvertor;

public class ArabicNumeralsConvertorDemo {

    public static void main(String[] args) {
        ArabicNumeralsConvertor convertor = new ArabicNumeralsConvertor();
        System.out.println(convertor.convert(2000101020));
        System.out.println(convertor.convert(1020));
        System.out.println(convertor.convert(99));
        System.out.println(convertor.convert(190990990));
        System.out.println(convertor.convert(1000));
        System.out.println(convertor.convert(10000));
        System.out.println(convertor.convert(100000));
        System.out.println(convertor.convert(1000001));
        System.out.println(convertor.convert(1000000));
        System.out.println(convertor.convert(10000000));
        System.out.println(convertor.convert(100000000));
        System.out.println(convertor.convert(1000000001));
    }
}
