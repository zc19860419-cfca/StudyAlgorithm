package com.sadk.algorithm.bruteForce;

/**
 * @author zhangchong
 * @CodeReviewer zhangqingan
 * @Description 已知固定值的剪枝器
 */
public interface FixValuePruner {
    /**
     * 剪枝器剪枝操作
     * @param ci
     * @param index
     * @return
     */
    boolean pruning(CharItem[] ci, int index);
}
