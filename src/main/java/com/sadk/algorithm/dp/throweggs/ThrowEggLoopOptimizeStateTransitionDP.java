package com.sadk.algorithm.dp.throweggs;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;
import com.sadk.algorithm.utils.Debugger;

/**
 * @Author: zhangchong
 * @Description: 优化状态转移方程
 */
public class ThrowEggLoopOptimizeStateTransitionDP implements ThrowEggDP {
    private static final Logger LOG = LoggerFactory.getLogger(ThrowEggLoopOptimizeStateTransitionDP.class);
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
     * </p>
     *
     *
     * <pre>
     * f(i,j) = min{max{f(i-1,w-1),f(i,j-w)}+1|1<=w<=j}             ①
     *      min{max{
     *          f(i-1,0) f(i-1,1) ... f(i-1,j-1)
     *          f(i,j-1) f(i,j-2) ... f(i,0)
     *      }}
     *
     * f(i,j) >= f(i,j-1)                                             ②
     *      f(i,j) = min{max{f(i-1,w-1),f(i,j-w)}+1|1<=w<=j}
     *      f(i,j) <= max{f(i-1,w-1),f(i,j-w)}+1|1<=w<=j
     *      令w=1,则  f(i,j) <= max{f(i-1,0),f(i,j-1)}+1=f(i,j-1)+1
     *      即 f(i,j)<=f(i,j-1)+1 (j>=1)
     *
     * f(i,j) <= f(i,j-1)+1 (j>=1)                                    ③
     *      又∵f(i,j)>=f(i,j-1), ∴ f(i,j-1)<=f(i,j)<=f(i,j-1)+1
     *      推理出:
     *          若某个决策 w 可以使 f(i,j)=f(i,j-1),则 f(i,j)取最小值 => f(i,j)=f(i,j-1)
     *          若所有决策 w 都不能使 f(i,j)=f(i,j-1),则 f(i,j)取最大值 => f(i,j)=f(i,j-1)+1 (且必存在这样的 w 使得 f(i,j)=f(i,j-1)+1)
     *      由此设指针 p 满足:
     *      f(i,p) < f(i,j-1) 且 f(i,p+1)=f(i,j-1);
     *          f(i,p)=f(i,j-1)-1
     *          f(i,p+1)=f(i,p+2)=...=f(i,j-1)
     *      在计算f(i,j)时,令p=j-w => w=j-p
     *      令 tmp=max{f(i-1,w-1),f(i,j-w)}+1 => tmp=max{f(i-1,j-p-1),f(i,p)}+1
     *      Case1.若 f(i,p)>=f(i-1,j-p-1) => tmp=f(i,p)+1=f(i,j-1)-1+1=f(i,j-1) =>tmp=f(i,j-1)
     *      这说明当前决策 w 可以使得 f(i,j)=f(i,j-1) ∴f(i,j)=f(i,j-1)
     *
     *      Case2.若 f(i,p)<f(i-1,j-p-1) => tmp=f(i-1,j-p-1)+1
     *          Case2.1 当 p'<p 时
     *          必有 f(i-1,j-p'-1)>=f(i-1,j-p-1)>f(i,p)
     *          ∴ max{f(i,p'),f(i-1,j-p'-1)}+1 >= f(i-1,j-p'-1)+1 >= f(i-1,j-p-1)+1 > f(i,p)+1=f(i,j-1)
     *          即 max{f(i,p'),f(i-1,j-p'-1)}+1 > f(i,j-1) =>  max{f(i,p'),f(i-1,j-p'-1)}+1 >= f(i,j-1)+1
     *          f(i,j)>=max{f(i,p'),f(i-1,j-p'-1)}+1>= f(i,j-1)+1
     *          综上:f(i,j)>=f(i,j-1)+1 无法使得 f(i,j)=f(i,j-1)
     *
     *          Case2.1 当 p'=p 时
     *          max{f(i,p'),f(i-1,j-p'-1)}+1 >= f(i-1,j-p'-1)+1 > f(i,p')+1 = f(i,p)+1=f(i,j-1)
     *          即 max{f(i,p'),f(i-1,j-p'-1)}+1 > f(i,j-1) =>  max{f(i,p'),f(i-1,j-p'-1)}+1 >= f(i,j-1)+1
     *          f(i,j)>=max{f(i,p'),f(i-1,j-p'-1)}+1>= f(i,j-1)+1
     *          综上:f(i,j)>=f(i,j-1)+1 无法使得 f(i,j)=f(i,j-1)
     *
     *          Case2.3 当 p'>p 时
     *          必有 f(i,p')>f(i,p),此时 f(i,p')=f(i,j-1)
     *          max{f(i,p'),f(i-1,j-p'-1)}+1 >= f(i,p')+1 =f(i,j-1)+1
     *          f(i,j)>=max{f(i,p'),f(i-1,j-p'-1)}+1>= f(i,j-1)+1
     *          综上:f(i,j)>=f(i,j-1)+1 无法使得 f(i,j)=f(i,j-1)
     *      综上:当f(i,p)<f(i-1,j-p-1)时,任何决策都不能使得 f(i,j)=f(i,j-1),所以此时f(i,j)=f(i,j-1)+1
     *
     *      因此,只需要确定f(i,p) 与 f(i-1,j-p-1) 的大小关系便可直接确定 f(i,j) 的取值
     *      注意:
     *          1.f(i,1)需要特殊处理,即令 f(i,1)=1 因为p初值为0,并不满足 f(i,p)=f(i,j-1)-1
     *          2.若f(i,j)=f(i,j-1)+1,则将p 赋值为 j-1
     *
     *
     *      f(i,j)=min{max{
     *          f(i-1,0) f(i-1,1)   ...   f(i-1,j-2)  f(i-1,j-1)
     *       =>[                 ...     f(i-1,p')  ]    | [ f(i-1,p'+1)...f(i-1,j-1)]
     *                                     |                       |
     *         [<=f(i-1,j-1)-1 area  =f(i-1,j-1)-1 area] | [  =f(i-1,j-1) area    ]
     *
     *          f(i,0)   f(i,1)     ...   f(i,j-2)    f(i,j-1)
     *       =>[                 ...     f(i,p)    ] | [ f(i,p+1)...f(i,j-1)]
     *                                     |                 |
     *         [<=f(i,j-1)-1 area  =f(i,j-1)-1 area] | [  =f(i,j-1) area    ]
     *      }}
     *      一旦找到 p 点,后续都不用算,所以状态迁移时间复杂度为O(1)
     * </pre>
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
                    if (brokenCase >= notBrokenCase) {
                        high = drop - 1;
                        result = Math.min(result, brokenCase + 1);
                    } else {
                        low = drop + 1;
                        result = Math.min(result, notBrokenCase + 1);
                    }
                }
                state[egg][floor] = result;
                LOG.debug("state({},{})=>{}", egg, floor, result);
            }

        }

        return state[eggs][floors];
    }

    /**
     * 初始化状态转移矩阵 egg 和 floor 0以及1的情况都已经考虑 后续将忽略
     *
     * @param eggs
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
