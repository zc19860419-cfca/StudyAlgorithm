package com.sadk.algorithm.bruteForce;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;

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
    public void onCharListReady(CharItem[] ci, String minuendStr, String subtrahendStr, String diffStr) {
//        logger.info("ci={}", Arrays.toString(ci));
        int minuend = makeInt(minuendStr, ci);
        int subtrahend = makeInt(subtrahendStr, ci);
        int diff = makeInt(diffStr, ci);
        if (minuend - subtrahend == diff) {
            logger.info("{}-{}={}", minuend, subtrahend, diff);
        }

    }

    private int makeInt(String s, CharItem[] ci) {
        for (int i = 0; i < ci.length; i++) {
            s = s.replace(ci[i].c, Character.forDigit(ci[i].value, 10));
        }
        return Integer.parseInt(s);
    }
}
