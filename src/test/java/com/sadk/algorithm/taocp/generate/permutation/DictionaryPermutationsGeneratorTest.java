package com.sadk.algorithm.taocp.generate.permutation;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author zhangchong
 * @CodeReviewer zhangqingan
 * @Description
 */
public class DictionaryPermutationsGeneratorTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGenerator() {
        Integer[] data = new Integer[]{1, 2, 2, 3};
        DictionaryPermutationsGenerator generator = new DictionaryPermutationsGenerator();
        Integer[] next = generator.generator(data);
        System.out.println(Arrays.toString(next));

    }
}