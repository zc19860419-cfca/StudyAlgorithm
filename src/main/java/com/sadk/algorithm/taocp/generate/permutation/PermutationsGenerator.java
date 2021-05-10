package com.sadk.algorithm.taocp.generate.permutation;

/**
 * @author zhangchong
 * @CodeReviewer zhangqingan
 * @Description 所有排列生成器
 */
public interface PermutationsGenerator<T> {
    T[] next();
}
