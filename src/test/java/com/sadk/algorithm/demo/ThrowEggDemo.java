package com.sadk.algorithm.demo;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.dp.throweggs.ThrowEggDP;
import com.sadk.algorithm.dp.throweggs.ThrowEggLoopBinarySearchDP;

/**
 * @Author: zhangchong
 * @Description:
 */
public class ThrowEggDemo {
    private static final Logger LOG = LoggerFactory.getLogger(ThrowEggDemo.class);
    public static void main(String[] args) {
        ThrowEggDP throwEggDP = new ThrowEggLoopBinarySearchDP();
        final long result = throwEggDP.dp(3, 10000);
        LOG.info("min={},count={}", result, throwEggDP.getCount());
    }
}
