package com.sadk.algorithm.dp.throweggs;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;
import com.sadk.algorithm.utils.Debugger;

import java.util.Arrays;

/**
 * @Author: zhangchong
 * @Description:
 */
public class ThrowEggLoopDP implements ThrowEggDP {
    private static final Logger LOG = LoggerFactory.getLogger(ThrowEggLoopDP.class);
    private static int f[][] = null;
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
        if (null == f) {
            f = new int[eggs + 1][floors + 1];

            for (int i = 1; i <= eggs; i++) {
                for (int j = 1; j <= floors; j++) {
                    f[i][j] = floors;
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

        int res;
        if (1 == eggs) {
            res = f[eggs][floors];
        } else if (0 == floors) {
            res = f[eggs][floors];
        } else {
            int tmp;
            for (int i = 2; i <= eggs; i++) {
                for (int j = 1; j <= floors; j++) {
                    tmp = j;
                    for (int w = 1; w <= j; w++) {
                        count++;
                        tmp = Math.min(tmp,
                                //在第w层扔鸡蛋,两种情况:蛋碎 或者 蛋未碎.最坏情况下选两种情况的最大值
                                Math.max(
                                        f[i - 1][w - 1],    // eggs broken (worst case minimum number)
                                        f[i][j - w]         // eggs not broken (worst case minimum number)
                                ) + 1                        // count this time that has been thrown on the w-th floor
                        );
                    }
                    f[i][j] = tmp;
                    LOG.debug("f({},{})=>{}", i, j, tmp);
                }

            }
            res = f[eggs][floors];
        }

        return res;
    }

    @Override
    public long getCount() {
        return count;
    }

    private void dumpMatrix(int f[][]) {
        StringBuilder builder = new StringBuilder(1024);
        builder.append("[\n");
        Arrays.asList(f).stream().forEach(row -> {
            for (int i = 0; i < row.length; i++) {
                if (i > 0) {
                    builder.append(",");
                }
                builder.append(String.format("%4d", row[i]));
            }
            builder.append("\n");
        });
        builder.append("]");
        LOG.debug("f={}", builder.toString());
    }

}
