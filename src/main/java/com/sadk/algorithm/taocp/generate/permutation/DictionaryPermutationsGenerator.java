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
public class DictionaryPermutationsGenerator implements PermutationsGenerator<Integer> {

    private final int[] data;
    private final int n;
    private int count = 0;
    private boolean end = false;
    private boolean debug = false;

    public DictionaryPermutationsGenerator(Integer[] org) {
        Args.check(org != null, "forbidden org null");
        Args.check(org.length > 0, "forbidden org length == 0");

        this.data = Arrays.stream(org).mapToInt(Integer::valueOf).toArray();
        this.n = data.length;
        if (n > 2) {
            Arrays.sort(this.data);
        }
    }


    @Override
    public Integer[] next() {
        if (end) {
            throw new NoSuchElementException("no more permutation[" + count + "]");
        }
        if (n <= 2) {
            ArraysUtils.swap(data, 0, 1);
            end = true;
        } else {
            int j = n - 2;
            while (data[j] >= data[j + 1]) {
                j = j - 1;
                if (j == -1) {
                    break;
                }
            }
            //  找到第一个升序相邻对 data[j] < data[j + 1]，从而得知右侧的所有相邻对都是降序 data[j + 1] 是峰值

            if (j <= -1) {
                end = true;
                throw new NoSuchElementException("no more permutation[" + count + "]");
            }
            int nMinus1 = n - 1;
            int l = nMinus1;
            while (data[j] >= data[l]) {
                l = l - 1;
            }
//              data[n-1] <= ... <= data[l+1] <= data[j] < data[l] <= data[l-1] <= ... <=data[j+1]
            ArraysUtils.swap(data, j, l);
//              data[n-1] <= ... <= data[l+1] <= data[l] < data[j] <= data[l-1] <= ... <=data[j+1]
            if (debug) {
                System.out.println("[" + count + "] after  3:" + Arrays.toString(data));
            }
            int k = j + 1;
            l = nMinus1;
            int debugk = 0;
            int debugl = 0;
            if (debug) {
                debugk = k;
                debugl = l;
                System.out.println("[" + count + "] before 4:" + ArraysUtils.toString(data, debugk, debugl));
            }

            while (k < l) {
                ArraysUtils.swap(data, k, l);
                k = k + 1;
                l = l - 1;
            }
            if (debug) {
                System.out.println("[" + count + "] after  4:" + ArraysUtils.toString(data, debugk, debugl));
            }
            //k>=l
        }
        if (debug) {
            System.out.println("[" + count + "] final:" + Arrays.toString(data));
        }
        count++;
        return Arrays.stream(data).boxed().toArray(Integer[]::new);
    }
}
