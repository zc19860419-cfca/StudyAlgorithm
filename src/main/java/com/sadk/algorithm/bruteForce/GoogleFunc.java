package com.sadk.algorithm.bruteForce;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class GoogleFunc {
    private static final Logger logger = LoggerFactory.getLogger(GoogleFunc.class);
    /**
     * 0-9
     */
    private static final int MAX_NUMBER_COUNT = 10;

    private final FixValuePruner fixValuePruner;
    private final String minuendStr;
    private final String subtrahendStr;
    private final String diffStr;
    public static final CharValue[] CHAR_VALUES = new CharValue[]{
            new CharValue(false, 0),
            new CharValue(false, 1),
            new CharValue(false, 2),
            new CharValue(false, 3),
            new CharValue(false, 4),
            new CharValue(false, 5),
            new CharValue(false, 6),
            new CharValue(false, 7),
            new CharValue(false, 8),
            new CharValue(false, 9),
    };

    public GoogleFunc(String minuendStr, String subtrahendStr, String diffStr, FixValuePruner fixValuePruner) {
        this.minuendStr = minuendStr;
        this.subtrahendStr = subtrahendStr;
        this.diffStr = diffStr;
        this.fixValuePruner = fixValuePruner;

        logger.debug("{}-{}={}", minuendStr, subtrahendStr, diffStr);
    }

    public boolean search(CharItem[] ci, int index, CharListReadyFunc callback) {
        boolean result = false;
        if (index == ci.length) {
            result = callback.onCharListReady(ci, minuendStr, subtrahendStr, diffStr);
        }
        boolean tmpResult;
        for (int i = 0; i < MAX_NUMBER_COUNT && index < ci.length; i++) {
            if (isValueValid(ci, index, CHAR_VALUES[i])) {
                CHAR_VALUES[i].used = true;
                ci[index].value = CHAR_VALUES[i].value;
                tmpResult = search(ci, index + 1, callback);
                if (tmpResult) {
                    result = tmpResult;
                }
                CHAR_VALUES[i].used = false;
            }
        }
        return result;
    }

    private boolean isValueValid(CharItem[] ci, int index, CharValue charValue) {
        boolean result = false;
        if (!charValue.used) {
            if (index < ci.length) {
                final CharItem charItem = ci[index];
                result = (charItem.leading && charValue.value != 0) || !charItem.leading;
                if (result){
                    fixValuePruner.pruning(ci, index);
                }
            }
        }
        return result;
    }
}
