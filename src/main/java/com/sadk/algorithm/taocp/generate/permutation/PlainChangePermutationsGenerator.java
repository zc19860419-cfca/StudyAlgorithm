package com.sadk.algorithm.taocp.generate.permutation;

import com.sadk.algorithm.utils.Args;
import com.sadk.algorithm.utils.ArraysUtils;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author zhangchong
 * @CodeReviewer zhangqingan
 * @Description
 */
public class PlainChangePermutationsGenerator implements PermutationsGenerator<Integer> {

    private final int[] a;
    /**
     * 控制 c[] 逆表条目c[j] 改变的方向
     */
    private final int[] o;
    /**
     * 逆表条目c[j] 代表比j小但是位于j右边的元素的个数
     */
    private final int[] c;
    private final int n;
    private int count = 0;
    private boolean end = false;
    private boolean debug = true;

    public PlainChangePermutationsGenerator(Integer[] org) {
        Args.check(org != null, "PlainChange#forbidden org null");
        Args.check(org.length > 0, "PlainChange#forbidden org length == 0");

        this.a = Arrays.stream(org).mapToInt(Integer::valueOf).toArray();
        this.n = a.length;
        this.o = new int[n];
        this.c = new int[n];
        Arrays.fill(this.o, 1);
        Arrays.fill(this.c, 0);
    }


    @Override
    public Integer[] next() {
        // P2
        if (end) {
            throw new NoSuchElementException("PlainChange#no more permutation[" + count + "]");
        }
        if (n <= 2) {
            ArraysUtils.swap(a, 0, 1);
            end = true;
        } else {
            // P3
            int j = n - 1;
            // s是使得c[k]=k-1的下标k>j的个数
            int s = 0;
            // P4
            int q;
            while (true) {
                q = c[j] + o[j];

                if (q >= 0 && q != j + 1) {
                    // P5
                    if (debug) {
                        System.out.println("[" + count + "] before P5:" + Arrays.toString(a));
                        System.out.println("[" + count + "] " + j + " - " + c[j] + " + " + s + "=" + (j - c[j] + s));
                        System.out.println("[" + count + "] " + j + " - " + q + " + " + s + "=" + (j - q + s));
                    }
                    ArraysUtils.swap(a, j - c[j] + s, j - q + s);
                    if (debug) {
                        System.out.println("[" + count + "] after  P5:" + Arrays.toString(a));
                    }

                    c[j] = q;
                    if (debug) {
                        System.out.println("[" + count + "] change c :" + Arrays.toString(c));
                    }
                    //返回P2
                    break;
                }
                // q = c[j] + o[j];
                // 只可能是：c[j]=0 o[j]=-1
                if (q < 0) {
                    // P7
                    o[j] = -o[j];
                    j = j - 1;
                    if (debug) {
                        System.out.println("[" + count + "] after  P7:" + Arrays.toString(o));
                    }
                    // 返回P4
                } else if (q == j + 1) {
                    // P6
                    if (j == 0) {
                        end = true;
                        throw new NoSuchElementException("PlainChange#no more permutation[" + count + "]");
                    }
                    s = s + 1;
                    if (debug) {
                        System.out.println("[" + count + "] after  P6:s=" + s);
                    }
                    // P7
                    o[j] = -o[j];
                    j = j - 1;
                    if (debug) {
                        System.out.println("[" + count + "] after  P7:" + Arrays.toString(o));
                    }
                    // 返回P4
                }
            }
        }

        count++;
        return Arrays.stream(a).boxed().toArray(Integer[]::new);
    }
}
