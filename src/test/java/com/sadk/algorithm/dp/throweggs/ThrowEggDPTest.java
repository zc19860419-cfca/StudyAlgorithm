package com.sadk.algorithm.dp.throweggs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: zhangchong
 * @Description:
 */
public class ThrowEggDPTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void dp_ThrowEggLoopBinarySearchDP() {
        long result;
        ThrowEggDP throwEggDP;

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(1, 1);
        Assert.assertEquals(1, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(1, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(1, 3);
        Assert.assertEquals(3, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(2, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(2, 3);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(2, 4);
        Assert.assertEquals(3, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(3, 1);
        Assert.assertEquals(1, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(3, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(3, 3);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        result = throwEggDP.dp(3, 4);
        Assert.assertEquals(3, result);
    }

    @Test
    public void dp_ThrowEggRecursiveBinarySearchDP() {
        long result;
        ThrowEggDP throwEggDP;

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(1, 1);
        result = throwEggDP.dp(1, 1);
        Assert.assertEquals(1, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(1, 2);
        result = throwEggDP.dp(1, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(1, 3);
        result = throwEggDP.dp(1, 3);
        Assert.assertEquals(3, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(2, 2);
        result = throwEggDP.dp(2, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(2, 3);
        result = throwEggDP.dp(2, 3);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(2, 4);
        result = throwEggDP.dp(2, 4);
        Assert.assertEquals(3, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(3, 1);
        result = throwEggDP.dp(3, 1);
        Assert.assertEquals(1, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(3, 2);
        result = throwEggDP.dp(3, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(3, 3);
        result = throwEggDP.dp(3, 3);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggRecursiveBinarySearchDP(3, 4);
        result = throwEggDP.dp(3, 4);
        Assert.assertEquals(3, result);


    }

    @Test
    public void dp_test() {
        ThrowEggDP throwEggDP = new ThrowEggRecursiveBinarySearchDP(3, 100);
        System.out.println(throwEggDP.dp(3, 100));

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        System.out.println(throwEggDP.dp(3, 100));

//        throwEggDP = new ThrowEggRecursiveDP();
//        System.out.println(throwEggDP.dp(3, 30));

        throwEggDP = new ThrowEggLoopDP();
        System.out.println(throwEggDP.dp(3, 100));
    }

    @Test
    public void dp_ThrowEggLoopNewFunctionDP() {
        long result;

        ThrowEggDP throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(1, 1);
        Assert.assertEquals(1, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(1, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(1, 3);
        Assert.assertEquals(3, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(2, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(2, 3);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(2, 4);
        Assert.assertEquals(3, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(3, 1);
        Assert.assertEquals(1, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(3, 2);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(3, 3);
        Assert.assertEquals(2, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(3, 4);
        Assert.assertEquals(3, result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        result = throwEggDP.dp(3, 100);
        Assert.assertEquals(9, result);
    }

    @Test
    public void dpCount() {
        final int eggs = 30;
        final int floors = 100;
        long dp;
        long count;
        String result;

        ThrowEggDP throwEggDP = new ThrowEggRecursiveBinarySearchDP(eggs, floors);
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);
    }

    @Test
    public void dpCount1() {
        final int eggs = 30;
        final int floors = 30;
        long dp;
        long count;
        String result;

        ThrowEggDP throwEggDP = new ThrowEggRecursiveBinarySearchDP(eggs, floors);
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);
    }

    @Test
    public void dpCount2() {
        final int eggs = 300;
        final int floors = 100;
        long dp;
        long count;
        String result;

        ThrowEggDP throwEggDP = new ThrowEggRecursiveBinarySearchDP(eggs, floors);
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopBinarySearchDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);

        throwEggDP = new ThrowEggLoopNewFunctionDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);
    }

    @Test
    public void dpCount_test() {
        final int eggs = 30;
        final int floors = 40;
        long dp;
        long count;
        String result;

        ThrowEggDP throwEggDP = new ThrowEggLoopNewFunctionDP();
        dp = throwEggDP.dp(eggs, floors);
        count = throwEggDP.getCount();
        result = String.format("%10d,%10d", dp, count);
        System.out.println(result);
    }
}