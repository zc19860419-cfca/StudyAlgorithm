package com.sadk.algorithm.binarysearch;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;

/**
 * @Author: zhangchong
 * @Description:
 */
public class SimpleBinarySearch2 implements BinarySearch<Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleBinarySearch2.class);


    /**
     * 循环不变式为：lower<upper && values[lower]<target<=values[upper]
     *
     * @param values
     * @param target
     * @return
     */
    @Override
    public int search(Integer[] values, Integer target) {
        Args.notNull(values, "SimpleBinarySearch2@search values");
        int found;
        if (0 == values.length) {
            found = -1;
        } else {
            int lower = -1;
            int upper = values.length;
            int mid;
            while (lower + 1 != upper) {
                //循环不变式为：lower<upper && values[lower]<target<=values[upper]
                mid = lower + ((upper - lower) >> 1);
//                LOG.info("({},{})->{}", lower, upper, mid);
                if (values[mid] < target) {
                    lower = mid;
                } else {
                    upper = mid;
                }
            }
            //断言：lower+1=upper && values[lower]<target<=values[upper]
            found = upper;
            if (found >= values.length || values[found] != target) {
                found = -1;
            }
        }

        return found;
    }
}
