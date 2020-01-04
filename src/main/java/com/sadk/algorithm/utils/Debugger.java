package com.sadk.algorithm.utils;

import cfca.org.slf4j.Logger;

import java.util.Arrays;

/**
 * @Author: zhangchong
 * @Description:
 */
public class Debugger {

    public static void dumpMatrix(int f[][], Logger logger) {
        StringBuilder builder = new StringBuilder(1024);
        builder.append("[\n");
        Arrays.asList(f).stream().forEach(row -> {
            for (int i = 0; i < row.length; i++) {
                if (i > 0) {
                    builder.append(",");
                }
                builder.append(String.format("%4d", row[i]));
            }
            builder.append("\n");
        });
        builder.append("]");
        logger.debug("f={}", builder.toString());
    }
}
