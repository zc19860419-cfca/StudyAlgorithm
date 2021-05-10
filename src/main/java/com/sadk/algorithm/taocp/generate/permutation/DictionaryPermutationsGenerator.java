package com.sadk.algorithm.taocp.generate.permutation;

import com.sadk.algorithm.utils.Args;

import java.util.Arrays;

/**
 * @author zhangchong
 * @CodeReviewer zhangqingan
 * @Description
 */
public class DictionaryPermutationsGenerator implements PermutationsGenerator<Integer> {

    private final int[] data;
    private final int n;
    private boolean end = false;

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
            return Arrays.stream(data).boxed().toArray(Integer[]::new);
        }
        if (n <= 2) {
            swap(data, 0, 1);
        } else {
            int j = n - 2;
            while (data[j] >= data[j + 1]) {
                j = j - 1;
                if (j == -1) {
                    break;
                }
            }

            if (j > -1) {
                int l = n - 1;
                while (data[j] >= data[l]) {
                    l = l - 1;
                }
                //data[j] < data[l]
                swap(data, j, l);
//                System.out.println(Arrays.toString(data));
                int k = j + 1;
                l = n - 1;
                while (k < l) {
                    swap(data, k, l);
//                    System.out.println(Arrays.toString(data));
                    k = k + 1;
                    l = l - 1;
                }
                //k>=l
            } else {
                end = true;
            }
        }
        return Arrays.stream(data).boxed().toArray(Integer[]::new);
    }

    private void swap(int[] array, int j, int l) {
        if (array == null || array.length == 0 || j >= array.length || l >= array.length) {
            return;
        }
        array[j] ^= array[l];
        array[l] ^= array[j];
        array[j] ^= array[l];
    }
}
