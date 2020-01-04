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

    private final String minuendStr;
    private final String subtrahendStr;
    private final String diffStr;
    private static final CharValue[] CHAR_VALUES = new CharValue[]{
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

    public GoogleFunc(String minuendStr, String subtrahendStr, String diffStr) {
        this.minuendStr = minuendStr;
        this.subtrahendStr = subtrahendStr;
        this.diffStr = diffStr;

        logger.info("{}-{}={}", minuendStr, subtrahendStr, diffStr);
    }

    public void search(CharItem[] ci, int index, CharListReadyFunc callback) {
        if (index == ci.length) {
            callback.onCharListReady(ci, minuendStr, subtrahendStr, diffStr);
            return;
        }

        for (int i = 0; i < MAX_NUMBER_COUNT; i++) {
            if (isValueValid(ci[index], CHAR_VALUES[i])) {
                CHAR_VALUES[i].used = true;
                ci[index].value = CHAR_VALUES[i].value;
                search(ci, index + 1, callback);
                CHAR_VALUES[i].used = false;
            }
        }
    }

    private boolean isValueValid(CharItem charItem, CharValue charValue) {
        boolean result = false;
        if (!charValue.used) {
            result = (charItem.leading && charValue.value != 0) || !charItem.leading;
        }

        return result;
    }
}
