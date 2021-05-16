package com.sadk.algorithm.taocp.generate.permutation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author zhangchong
 * @CodeReviewer zhangqingan
 * @Description
 */
public class PlainChangePermutationsGeneratorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDifferentGenerator1() {
        Integer[] data = new Integer[]{1, 2, 3};
        PlainChangePermutationsGenerator generator = new PlainChangePermutationsGenerator(data);
        String[] expected = new String[]{
                "[1, 2, 3]",
                "[1, 3, 2]",
                "[2, 1, 3]",
                "[2, 3, 1]",
                "[3, 1, 2]",
                "[3, 2, 1]",
        };
        Arrays.sort(expected);

        boolean[] visited = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
        };

        visited[Arrays.binarySearch(expected, Arrays.toString(data))] = true;

        int hit;
        String actual;
        for (int i = 0; i < 5; i++) {
            data = generator.next();
            actual = Arrays.toString(data);
            System.out.println(actual);
            hit = Arrays.binarySearch(expected, actual);
            Assert.assertTrue(hit != -1);
            visited[hit] = true;
        }

        for (int i = 0; i < visited.length; i++) {
            Assert.assertTrue(visited[i]);
        }

        expectedEx.expect(NoSuchElementException.class);
        expectedEx.expectMessage("PlainChange#no more permutation");
        generator.next();
    }

    @Test
    public void testDifferentGenerator2() {
        Integer[] data = new Integer[]{3, 2, 1};
        PlainChangePermutationsGenerator generator = new PlainChangePermutationsGenerator(data);
        String[] expected = new String[]{
                "[1, 2, 3]",
                "[1, 3, 2]",
                "[2, 1, 3]",
                "[2, 3, 1]",
                "[3, 1, 2]",
                "[3, 2, 1]",
        };
        Arrays.sort(expected);

        boolean[] visited = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
        };

        visited[Arrays.binarySearch(expected, Arrays.toString(data))] = true;

        int hit;
        String actual;
        for (int i = 0; i < 5; i++) {
            data = generator.next();
            actual = Arrays.toString(data);
            System.out.println(actual);
            hit = Arrays.binarySearch(expected, actual);
            Assert.assertTrue(hit != -1);
            visited[hit] = true;
        }

        for (int i = 0; i < visited.length; i++) {
            Assert.assertTrue(visited[i]);
        }

        expectedEx.expect(NoSuchElementException.class);
        expectedEx.expectMessage("PlainChange#no more permutation");
        generator.next();
    }

    @Test
    public void testDifferentGenerator3() {
        Integer[] data = new Integer[]{1, 2, 3, 4};
        PlainChangePermutationsGenerator generator = new PlainChangePermutationsGenerator(data);
        String[] expected = new String[]{
                "[1, 2, 3]",
                "[1, 3, 2]",
                "[2, 1, 3]",
                "[2, 3, 1]",
                "[3, 1, 2]",
                "[3, 2, 1]",
        };
        Arrays.sort(expected);

        boolean[] visited = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
        };

//        visited[Arrays.binarySearch(expected, Arrays.toString(data))] = true;

        int hit;
        String actual;
        for (int i = 0; i < 11; i++) {
            data = generator.next();
            actual = Arrays.toString(data);
            System.out.println(actual);
//            hit = Arrays.binarySearch(expected, actual);
//            Assert.assertTrue(hit != -1);
//            visited[hit] = true;
        }

//        for (int i = 0; i < visited.length; i++) {
//            Assert.assertTrue(visited[i]);
//        }
//
//        expectedEx.expect(NoSuchElementException.class);
//        expectedEx.expectMessage("PlainChange#no more permutation");
//        generator.next();
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