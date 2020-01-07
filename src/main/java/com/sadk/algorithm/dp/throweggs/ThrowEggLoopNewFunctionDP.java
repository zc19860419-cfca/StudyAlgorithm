package com.sadk.algorithm.dp.throweggs;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;

/**
 * @Author: zhangchong
 * @Description: 重新定义状态转移方程达到最终优化
 */
public class ThrowEggLoopNewFunctionDP implements ThrowEggDP {
    private static final Logger LOG = LoggerFactory.getLogger(ThrowEggLoopNewFunctionDP.class);
    private int state[][];
    private static long count = 0L;

    /**
     * 状态定义即为 用 j 个蛋尝试i次在最坏情况下能确定E 的最高楼层数,定义为g(i,j)
     * 当前有 j 个鸡蛋,可以尝试扔 i 次鸡蛋,最坏情况下最多能确切测试一栋 g(i,j) 层的楼
     * <p>
     * 比如说 g[1][7] = 7 表示: 现在有 1 个鸡蛋,允许你扔 7 次;
     * 这个状态下最多给你 7 层楼,使得你可以确定楼层 F 使得鸡蛋恰好摔不碎(一层一层线性探查)
     *
     * <p>
     * 边界条件:
     * 无论有多少鹰蛋,若只试 1 次都只能确定高度为 1  的楼
     * g(1,j)=1(j>=1)
     * 无论有多少鹰蛋,若只试 0 次都只能确定高度为 0  的楼
     * g(0,j)=0(j>=1)
     * 若只提供1 个鹰蛋,那么尝试i次 在最坏情况下可以在楼层数就是 i 的楼上确定E
     * g(i,1)=i(i>=1)
     * 若只提供0 个鹰蛋,那么尝试i次 在最坏情况下可以在楼层数就是 0 的楼上确定E
     * g(i,0)=0(i>=1)
     * </p>
     *
     * <pre>
     * 状态转移方程:
     * 假设第一次在某一层楼扔出鹰蛋
     * 碎了:则在后面的 (i-1) 次里,用 (j-1) 个蛋在下面的楼层中确定 E,为了使 g(i,j) 达到最大
     * 这就是一个子问题(满足独立子结构和最优子问题), 最高楼层数为: g(i-1,j-1)
     * 没碎:则在后面的 (i-1) 次里,用 (j-1) 个蛋在上面的楼层中确定 E,为了使 g(i,j) 达到最大
     * 这就是一个子问题(满足独立子结构和最优子问题), 最高楼层数为: g(i-1,j)
     * g(i,j)=g(i-1,j-1)+g(i-1,j)+1
     *
     * 给定M个蛋 和 N层楼
     * 目标是找到一个x,使得x 满足 g(x-1,M)<N 且 g(x,M)>=N,答案即为x
     * </pre>
     *
     * @param eggs   给定鹰蛋个数
     * @param floors 给定楼层
     * @return 最坏情况下确定鸡蛋坚硬度E所需要的最少次数
     */
    @Override
    public int dp(int eggs, int floors) {
        Args.notNegative(eggs, "eggs");
        Args.notNegative(floors, "floors");
        //第一步创建备忘录,采用滚动数组
        state = new int[floors + 1][eggs + 1];

        //第二步考虑边界
        //鹰蛋边界
        //若只提供1 个鹰蛋,那么尝试i次 在最坏情况下可以在楼层数就是 i 的楼上确定E
        //g(i,1)=i(i>=1)
        //g(0,1)=0
        //若只提供0 个鹰蛋,那么尝试i次 在最坏情况下可以在楼层数就是 0 的楼上确定E
        //g(i,0)=0(i>=1)
        //g(0,0)=0
        for (int times = 0; times <= floors; times++) {
            state[times][1] = times;//g(0,1)=0
            state[times][0] = 0;//g(0,0)=0
            count++;
        }

        //尝试次数边界
        //无论有多少鹰蛋,若只试 1 次都只能确定高度为 1  的楼
        //g(1,j)=1(j>=1)
        //g(1,0)=0
        state[1][0] = 0;
        count++;
        //无论有多少鹰蛋,若只试 0 次都只能确定高度为 0  的楼
        //g(0,j)=0(j>=1)
        for (int egg = 1; egg <= eggs; egg++) {
            //g(1,j)=1(j>=1)
            state[1][egg] = 1;
            state[0][egg] = 0;
            count++;
        }

        //第三步状态方程
        int low = 1;
        int high = eggs;
        int mid;
        int currentFloor;
        int nextFloor;
        int result = floors;
        int currentFloorBrokenCase;
        int currentFloorNonBrokenCase;
        int nextFloorBrokenCase;
        int nextFloorNonBrokenCase;
        boolean found = false;
        for (int time = 1; time < floors; time++) {
            while (low < high) {
                count++;
                mid = low + (high - low) / 2;
                currentFloorBrokenCase = state[time - 1][eggs - 1];
                currentFloorNonBrokenCase = state[time - 1][eggs];
                currentFloor = state[time][eggs] = currentFloorBrokenCase + currentFloorNonBrokenCase + 1;//currentTime
                // state(time+1,eggs)=state(time,eggs-1)+state(time,eggs)+1
                // to find the time To meet the conditions:
                // state[time][eggs]<floors && state[time + 1][eggs]>=floors,then the time value is the answer
                nextFloorBrokenCase = state[time][eggs - 1];
                nextFloorNonBrokenCase = state[time][eggs];
                nextFloor = state[time + 1][eggs] = nextFloorBrokenCase + nextFloorNonBrokenCase + 1;//currentTime
                if (currentFloor < floors && nextFloor >= floors) {
                    found = true;
                    result = time;
                    break;
                }
                if (currentFloor >= floors) {
                    high = mid - 1;
                } else if (nextFloor < floors) {
                    low = mid + 1;
                }
            }
            if (found) {
                break;
            }
        }

        return result;
    }

    @Override
    public long getCount() {
        return count;
    }


}
