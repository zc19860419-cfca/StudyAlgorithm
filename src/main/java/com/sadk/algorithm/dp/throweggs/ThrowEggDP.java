package com.sadk.algorithm.dp.throweggs;

/**
 * @Author: zhangchong
 * @Description:
 */
public interface ThrowEggDP {

    /**
     * 状态定义即为用i个蛋在j层楼上最坏情况下确定E所需要的最少次数，记为dp(i,j)。
     * dp(1,j)=j (j>=0)
     * dp(i,0)=0 (i>=0)
     *
     * @param eggs   给定鹰蛋个数
     * @param floors 给定楼层
     * @return 最坏情况下确定鸡蛋坚硬度E所需要的最少次数
     */
    long dp(int eggs, int floors);

    /**
     * 获取计算时间复杂度的操作次数
     * @return
     */
    long getCount();
}
