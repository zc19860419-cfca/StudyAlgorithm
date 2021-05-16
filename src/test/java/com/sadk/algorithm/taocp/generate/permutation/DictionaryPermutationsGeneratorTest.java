package com.sadk.algorithm.taocp.generate.permutation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author zhangchong
 * @CodeReviewer zhangqingan
 * @Description
 */
public class DictionaryPermutationsGeneratorTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
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

    @Test
    public void testDifferentGenerator() {
        Integer[] data = new Integer[]{1, 2, 3};
        DictionaryPermutationsGenerator generator = new DictionaryPermutationsGenerator(data);
        String[] expected = new String[]{
                "[1, 3, 2]",
                "[2, 1, 3]",
                "[2, 3, 1]",
                "[3, 1, 2]",
                "[3, 2, 1]",
        };
        for (int i = 0; i < 5; i++) {
            data = generator.next();
//            System.out.println(Arrays.toString(data));
            Assert.assertEquals(expected[i], Arrays.toString(data));
        }

    }

    @Test
    public void testDifferentGeneratorHard() {
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        DictionaryPermutationsGenerator generator = new DictionaryPermutationsGenerator(data);
        String[] expected = new String[]{
                "[1, 3, 2]",
                "[2, 1, 3]",
                "[2, 3, 1]",
                "[3, 1, 2]",
                "[3, 2, 1]",
        };
        for (int i = 0; i < 40319; i++) {
            data = generator.next();
            System.out.println(Arrays.toString(data));
//            Assert.assertEquals(expected[i], Arrays.toString(data));
        }

    }
}