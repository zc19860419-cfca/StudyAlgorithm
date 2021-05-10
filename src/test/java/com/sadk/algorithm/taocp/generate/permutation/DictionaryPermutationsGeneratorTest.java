package com.sadk.algorithm.taocp.generate.permutation;

import junit.framework.TestCase;
import org.junit.Assert;

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
        DictionaryPermutationsGenerator generator = new DictionaryPermutationsGenerator(data);
        String[] expected = new String[]{
                "[1, 2, 3, 2]",
                "[1, 3, 2, 2]",
                "[2, 1, 2, 3]",
                "[2, 1, 3, 2]",
                "[2, 2, 1, 3]",
                "[2, 2, 3, 1]",
                "[2, 3, 1, 2]",
                "[2, 3, 2, 1]",
                "[3, 1, 2, 2]",
                "[3, 2, 1, 2]",
                "[3, 2, 2, 1]"
        };
        for (int i = 0; i < 11; i++) {
            data = generator.next();
            Assert.assertEquals(expected[i], Arrays.toString(data));
        }

    }
}