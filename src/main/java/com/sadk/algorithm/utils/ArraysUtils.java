package com.sadk.algorithm.utils;

/**
 * @Author: zhangchong
 * @Description:
 */
public class ArraysUtils {
    public static String toString(int[] a, int start, int end) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        if (start < 0) {
            start = 0;
        }

        if (end > iMax) {
            end = iMax;
        }

        if (start > end)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = start; ; i++) {
            b.append(a[i]);
            if (i == end)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static void swap(int[] array, int j, int l) {
        if (array == null || array.length == 0 || j >= array.length || l >= array.length) {
            return;
        }
        array[j] ^= array[l];
        array[l] ^= array[j];
        array[j] ^= array[l];
    }
}
