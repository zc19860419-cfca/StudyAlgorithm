package com.sadk.algorithm.binarysearch;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;

/**
 * @Author: zhangchong
 * @Description:
 */
public class SimpleBinarySearch implements BinarySearch<Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleBinarySearch.class);


    /**
     * 循环不变式为：
     *  如果 target 存在，那么它一定存在于 values[lower...upper] 中
     * @param values
     * @param target
     * @return
     */
    @Override
    public int search(Integer[] values, Integer target) {
        Args.notNull(values, "SimpleBinarySearch@search values");
        int found;
        if (0 == values.length) {
            found = -1;
        } else {
            int lower = 0;
            int upper = values.length - 1;
            int mid;
            while (true) {
                if (lower > upper) {
                    found = -1;
                    break;
                }
                mid = lower + ((upper - lower) >> 1);
//                LOG.info("({},{})->{}", lower, upper, mid);
                if (values[mid] < target) {
                    lower = mid + 1;
                }
                if (values[mid] == target) {
                    found = mid;
                    break;
                }

                if (values[mid] > target) {
                    upper = mid -1;
                }
            }
        }

        return found;
    }
}
