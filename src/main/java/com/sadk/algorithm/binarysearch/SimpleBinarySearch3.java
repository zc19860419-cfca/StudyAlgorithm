package com.sadk.algorithm.binarysearch;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;

/**
 * @Author: zhangchong
 * @Description:
 */
public class SimpleBinarySearch3 implements BinarySearch<Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleBinarySearch3.class);


    /**
     * 循环不变式为：values[lower]<target<=values[lower+i] && i=2^j
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
            //i=2^9
            int i = 512;
            //invariant:values[lower]<target<=values[lower+i] && i=2^j
            //invariant:values[-1]<target<=values[511] && i=2^9
            if (values[511] < target) {
                //1000 - 512
                lower = 488;
            }
            //invariant:values[lower]<target<=values[lower+i] && i=2^j
            //invariant:values[488]<target<=values[1000] && i=2^9
            int nexti;
            while (i != 1) {
                //invariant:values[lower]<target<=values[lower+i] && i=2^j
                //i=2^9->2^8->...->2^0=1
                nexti = i >> 1;
                if (values[lower + nexti] < target) {
                    lower = lower + nexti;
                    i = nexti;
                    //(1)still:values[lower]<target
                    //(2)lower change bigger:so target<=values[lower+i]
                    //=> invariant:values[lower]<target<=values[lower+i] && i=2^j
                } else {
                    i = nexti;
                    //(1)still meet:target<=values[lower+i]
                    //(2)lower not change:so values[lower]<target
                    //=> invariant:values[lower]<target<=values[lower+i] && i=2^j
                }
            }

            //assert: i == 1 && values[lower]<target<=values[lower+i]
            found = lower + i;
            if (found > 1000 || values[found] != target) {
                found = -1;
            }
        }

        return found;
    }
}
