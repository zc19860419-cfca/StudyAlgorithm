package com.sadk.algorithm.binarysearch;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.utils.Args;

/**
 * @Author: zhangchong
 * @Description:
 */
public class SimpleBinarySearch5 implements BinarySearch<Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleBinarySearch5.class);


    /**
     * 循环不变式为：values[lower]<target<=values[lower+i] && i=2^j
     *
     * @param values
     * @param target
     * @return
     */
    @Override
    public int search(Integer[] values, Integer target) {
        Args.notNull(values, "SimpleBinarySearch5@search values");
        int found;
        if (0 == values.length) {
            found = -1;
        } else {
            int lower = -1;
            if (values[511] < target) {
                lower = 1000 - 512;
            }
            //assert: i=2^9(512) && values[lower]<target<=values[lower+i]
            //values[511] < target=> [values[lower=488]<target]<=values[lower+i=1000] (488,1000]
            //values[511] >=target=> values[lower=-1] [<target<=values[lower+i=511]]  (-1,511]
            if (values[lower + 256] < target) {
                lower += 256;
            }
            //assert: i=2^8(256) && values[lower]<target<=values[lower+i]
            //(488,1000] lower=488=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (744,1000]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (488,744]
            //(-1,511] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (255,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,255]
            if (values[lower + 128] < target) {
                lower += 128;
            }
            //assert: i=2^7(128) && values[lower]<target<=values[lower+i]
            //(255,511] lower=255=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (383,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (255,383]
            //(-1,255] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (127,255]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,127]
            if (values[lower + 64] < target) {
                lower += 64;
            }
            //assert: i=2^7(128) && values[lower]<target<=values[lower+i]
            //(255,511] lower=255=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (383,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (255,383]
            //(-1,255] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (127,255]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,127]
            if (values[lower + 32] < target) {
                lower += 32;
            }
            //assert: i=2^7(128) && values[lower]<target<=values[lower+i]
            //(255,511] lower=255=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (383,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (255,383]
            //(-1,255] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (127,255]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,127]
            if (values[lower + 16] < target) {
                lower += 16;
            }
            //assert: i=2^7(128) && values[lower]<target<=values[lower+i]
            //(255,511] lower=255=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (383,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (255,383]
            //(-1,255] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (127,255]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,127]
            if (values[lower + 8] < target) {
                lower += 8;
            }
            //assert: i=2^7(128) && values[lower]<target<=values[lower+i]
            //(255,511] lower=255=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (383,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (255,383]
            //(-1,255] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (127,255]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,127]
            if (values[lower + 4] < target) {
                lower += 4;
            }
            //assert: i=2^7(128) && values[lower]<target<=values[lower+i]
            //(255,511] lower=255=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (383,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (255,383]
            //(-1,255] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (127,255]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,127]
            if (values[lower + 2] < target) {
                lower += 2;
            }
            //assert: i=2^7(128) && values[lower]<target<=values[lower+i]
            //(255,511] lower=255=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (383,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (255,383]
            //(-1,255] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (127,255]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,127]
            if (values[lower + 1] < target) {
                lower += 1;
            }
            //assert: i=2^7(128) && values[lower]<target<=values[lower+i]
            //(255,511] lower=255=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (383,511]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (255,383]
            //(-1,255] lower=-1=>
            //  values[lower+i] < target=> lower=lower+i => 循环不变式: [values[lower]<target]<=values[lower+i] (127,255]
            //  values[lower+i] >=target=> 循环不变式: values[lower]<[target<=values[lower+i]] (-1,127]
            found = lower + 1;
            if (found > 1000 || values[found] != target) {
                found = -1;
            }
        }

        return found;
    }
}
