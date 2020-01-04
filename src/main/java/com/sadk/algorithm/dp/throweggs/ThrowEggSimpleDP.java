package com.sadk.algorithm.dp.throweggs;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;

/**
 * @Author: zhangchong
 * @Description:
 */
public class ThrowEggSimpleDP implements ThrowEggDP {
    private static final Logger LOG = LoggerFactory.getLogger(ThrowEggSimpleDP.class);
    private static long count = 0L;

    /**
     * 状态定义即为用i个蛋在j层楼上最坏情况下确定E所需要的最少次数，记为dp(i,j)。
     * dp(i,j)=min{max{dp(i-1,w-1),dp(i,j-w)}+1|1<=w<=j}
     * <p>
     * dp(1,j)=j (j>=0)
     * dp(i,0)=0 (i>=0)
     *
     * @param eggs   给定鹰蛋个数
     * @param floors 给定楼层
     * @return 最坏情况下确定鸡蛋坚硬度E所需要的最少次数
     */
    @Override
    public int dp(int eggs, int floors) {
        Args.notNegative(eggs, "eggs");
        Args.notNegative(floors, "floors");
        int res = floors;
        count++;
        if (1 == eggs) {
            res = floors;
        }else if (0 == floors){
            res = 0;
        }else {
            for (int w = 1; w <= floors; w++) {
                //最坏情况下的最少扔鸡蛋次数
                res = Math.min(res,
                        //在第w层扔鸡蛋,两种情况:蛋碎 或者 蛋未碎.最坏情况下选两种情况的最大值
                        Math.max(
                                dp(eggs - 1, w - 1),    // eggs broken (worst case minimum number)
                                dp(eggs, floors - w))           // eggs not broken (worst case minimum number)
                                + 1                        // count this time that has been thrown on the w-th floor
                );
            }
        }
        LOG.debug("f({},{})={}", eggs, floors, res);
        return res;
    }

    @Override
    public long getCount() {
        return count;
    }

}
