package com.sadk.algorithm.bruteForce;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class CharListReadyFuncImpl implements CharListReadyFunc {

    private static final Logger logger = LoggerFactory.getLogger(CharListReadyFuncImpl.class);

    /**
     * WWWDOT – GOOGLE = DOTCOM
     *
     * @param ci
     */
    public boolean onCharListReady(CharItem[] ci, String minuendStr, String subtrahendStr, String diffStr) {
        if (logger.isDebugEnabled()){
            logger.debug("ci={}", Arrays.toString(ci));
        }
        boolean result = false;
        int minuend = makeInt(minuendStr, ci);
        int subtrahend = makeInt(subtrahendStr, ci);
        int diff = makeInt(diffStr, ci);
        if (minuend - subtrahend == diff) {
            logger.info("{}-{}={}", minuend, subtrahend, diff);
            result = true;
        }
        return result;
    }

    private int makeInt(String s, CharItem[] ci) {
        for (int i = 0; i < ci.length; i++) {
            s = s.replace(ci[i].c, Character.forDigit(ci[i].value, 10));
        }
        return Integer.parseInt(s);
    }
}
