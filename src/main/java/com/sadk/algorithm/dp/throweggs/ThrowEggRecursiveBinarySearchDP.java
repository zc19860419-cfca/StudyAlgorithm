package com.sadk.algorithm.dp.throweggs;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;
import com.sadk.algorithm.utils.Debugger;

/**
 * @Author: zhangchong
 * @Description: 二分搜索优化的递归版本
 */
public class ThrowEggRecursiveBinarySearchDP implements ThrowEggDP {
    private static final Logger LOG = LoggerFactory.getLogger(ThrowEggRecursiveBinarySearchDP.class);
    private static long f[][] = null;
    private static long count = 0L;

    public ThrowEggRecursiveBinarySearchDP(int eggs, int floors){
        f = new long[eggs + 1][floors + 1];

        for (int i = 1; i <= eggs; i++) {
            for (int j = 1; j <= floors; j++) {
                f[i][j] = Long.MAX_VALUE;
                count++;
            }
        }

        for (int i = 0; i <= eggs; i++) {
            f[i][0] = 0;
            count++;
        }

        for (int j = 0; j <= floors; j++) {
            f[0][j] = 0;
            f[1][j] = j;
            count++;
        }

        if (LOG.isDebugEnabled()) {
            Debugger.dumpMatrix(f, LOG);
        }
    }

    /**
     * 状态定义即为用i个蛋在j层楼上最坏情况下确定E所需要的最少次数，记为dp(i,j)。
     * dp(i,j)=min{max{dp(i-1,w-1),dp(i,j-w)}+1|1<=w<=j}
     * dp(i,j) = min(max{(brokenCase[1],notBrokenCase[1])+1,...(brokenCase[j],notBrokenCase[j])+1})
     * <p>
     * dp(1,j)=j (j>=0)
     * dp(i,0)=0 (i>=0)
     *
     * @param eggs   给定鹰蛋个数
     * @param floors 给定楼层
     * @return 最坏情况下确定鸡蛋坚硬度E所需要的最少次数
     */
    @Override
    public long dp(int eggs, int floors) {
        Args.notNegative(eggs, "eggs");
        Args.notNegative(floors, "floors");
        count++;

        long res = Long.MAX_VALUE;
        if (Long.MAX_VALUE != f[eggs][floors]) {
            res = f[eggs][floors];
        } else {
            //用二分搜索代替线性搜索
            int low = 1;
            int high = floors;
            int mid;
            long brokenCase;
            long notBrokenCase;
            while (low <= high) {
                mid = low + (high - low) / 2;                   // throw egg at mid-th floor
                brokenCase = dp(eggs - 1, mid - 1);  // eggs broken in mid-th floor(worst case minimum number)
                notBrokenCase = dp(eggs, floors - mid);   // eggs not broken in mid-th floor(worst case minimum number)
                if (brokenCase > notBrokenCase) {
                    high = mid - 1;
                    res = Math.min(res, brokenCase + 1);
                } else {
                    low = mid + 1;
                    res = Math.min(res, notBrokenCase + 1);
                }
            }
            f[eggs][floors] = res;
            LOG.debug("f({},{})=>{}", eggs, floors, res);
        }

        return res;
    }

    @Override
    public long getCount() {
        return count;
    }


}
