package com.sadk.algorithm.demo;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.bruteForce.CharItem;
import com.sadk.algorithm.bruteForce.CharListReadyFunc;
import com.sadk.algorithm.bruteForce.CharListReadyFuncImpl;
import com.sadk.algorithm.bruteForce.GoogleFunc;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class GoogleFuncDemo {
    private static final Logger logger = LoggerFactory.getLogger(GoogleFuncDemo.class);

    /**
     * WWWDOT – GOOGLE = DOTCOM
     *
     * @param args
     */
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        final GoogleFunc googleFunc = new GoogleFunc("WWWDOT", "GOOGLE", "DOTCOM");
        CharListReadyFunc callback = new CharListReadyFuncImpl();
        CharItem[] ci = new CharItem[]{
                new CharItem('W', -1, true),
                new CharItem('D', -1, true),
                new CharItem('O', -1, false),
                new CharItem('T', -1, false),
                new CharItem('G', -1, true),
                new CharItem('L', -1, false),
                new CharItem('E', -1, false),
                new CharItem('C', -1, false),
                new CharItem('M', -1, false),
        };
        googleFunc.search(ci, 0, callback);
    }

    private static void test2() {
        final GoogleFunc googleFunc = new GoogleFunc("WWWDOT", "GOOGLE", "DOTCOM");
        CharListReadyFunc callback = new CharListReadyFuncImpl();
        CharItem[] ci = new CharItem[]{
                new CharItem('W', -1, true),
                new CharItem('D', -1, true),
                new CharItem('O', -1, false),
                new CharItem('T', -1, false),
                new CharItem('G', -1, true),
                new CharItem('L', -1, false),
                new CharItem('E', -1, false),
                new CharItem('C', -1, false),
                new CharItem('M', -1, false),
        };
        googleFunc.search(ci, 0, callback);
    }
}
