package com.sadk.algorithm.arabicNumerals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class ArabicNumeralsConvertorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void convert() {
        StringBuilder builder = new StringBuilder(20);
        builder.append("零");
        builder.insert(0,"一千两百");
        System.out.println(builder.toString());
    }
}