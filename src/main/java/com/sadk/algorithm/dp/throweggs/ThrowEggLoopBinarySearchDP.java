package com.sadk.algorithm.dp.throweggs;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;
import com.sadk.algorithm.utils.Debugger;

/**
 * @Author: zhangchong
 * @Description: 循环版本的二分搜索优化
 */
public class ThrowEggLoopBinarySearchDP implements ThrowEggDP {
    private static final Logger LOG = LoggerFactory.getLogger(ThrowEggLoopBinarySearchDP.class);
    private long state[][];
    private long count = 0L;

    /**
     * 状态定义即为用i个蛋在j层楼上最坏情况下确定E所需要的最少次数，记为dp(i,j)。
     * dp(i,j)=min{max{dp(i-1,w-1),dp(i,j-w)}+1|1<=w<=j}
     * <p>
     * 边界条件
     * dp(0,j)=0 (j>=0)
     * dp(1,j)=j (j>=0)
     * dp(i,0)=0 (i>=0)
     * dp(i,1)=1 (i>=0)
     *
     * @param eggs   给定鹰蛋个数
     * @param floors 给定楼层
     * @return 最坏情况下确定鸡蛋坚硬度E所需要的最少次数
     */
    @Override
    public long dp(int eggs, int floors) {
        Args.notNegative(eggs, "eggs");
        Args.notNegative(floors, "floors");
        this.state = initializeStateTransitionMatrix(eggs, floors);
        //第三步状态方程
        long result;
        long brokenCase;
        long notBrokenCase;
        int low;
        int high;
        int drop;
        for (int egg = 2; egg <= eggs; egg++) {
            for (int floor = 2; floor <= floors; floor++) {
                result = Integer.MAX_VALUE;
                low = 1;
                high = floor;
                //用二分搜索代替线性搜索
                while (low <= high) {
                    count++;
                    //在第 drop 层扔鸡蛋,两种情况:蛋碎 或者 蛋未碎.最坏情况下选两种情况的最大值
                    drop = low + (high - low) / 2;           // throw egg at drop-th floor
                    brokenCase = state[egg - 1][drop - 1];  // eggs broken (worst case minimum number)
                    notBrokenCase = state[egg][floor - drop];// eggs not broken (worst case minimum number)
                    if (brokenCase > notBrokenCase) {
                        high = drop - 1;
                        result = Math.min(result, brokenCase + 1);
                    } else {
                        low = drop + 1;
                        result = Math.min(result, notBrokenCase + 1);
                    }
                }
//                //线性搜索
//                for (int drop = 1; drop <= floor; drop++) {
//                    count++;
//                    //在第 drop 层扔鸡蛋,两种情况:蛋碎 或者 蛋未碎.最坏情况下选两种情况的最大值
//                    brokenCase = state[egg - 1][drop - 1];         //eggs broken (worst case minimum number)
//                    notBrokenCase = state[egg][floor - drop];      //eggs not broken (worst case minimum number)
//                    if (brokenCase > notBrokenCase) {
//                        result = Math.min(result, brokenCase + 1); //count this time that has been thrown
//                    }else {
//                        result = Math.min(result, notBrokenCase + 1);//count this time that has been thrown
//                    }
//                }
                state[egg][floor] = result;
                LOG.debug("state({},{})=>{}", egg, floor, result);
            }

        }

        return state[eggs][floors];
    }

    /**
     * 初始化状态转移矩阵 egg 和 floor 0以及1的情况都已经考虑 后续将忽略
     *  @param eggs
     * @param floors
     */
    private long[][] initializeStateTransitionMatrix(int eggs, int floors) {
        //第一步创建备忘录
        long state[][] = new long[eggs + 1][floors + 1];

        for (int i = 0; i <= eggs; i++) {
            for (int j = 0; j <= floors; j++) {
                state[i][j] = Integer.MAX_VALUE;
                count++;
            }
        }

        //第二步考虑边界
        //floors 边界
        for (int i = 0; i <= eggs; i++) {
            state[i][0] = 0;
            state[i][1] = 1;
            count++;
        }

        //eggs 边界
        for (int j = 0; j <= floors; j++) {
            state[0][j] = 0;
            state[1][j] = j;
            count++;
        }

        if (LOG.isDebugEnabled()) {
            Debugger.dumpMatrix(state, LOG);
        }
        return state;
    }

    @Override
    public long getCount() {
        return count;
    }


}
