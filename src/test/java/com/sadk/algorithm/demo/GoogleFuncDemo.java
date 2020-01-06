package com.sadk.algorithm.demo;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import com.sadk.algorithm.bruteForce.CharItem;
import com.sadk.algorithm.bruteForce.CharListReadyFunc;
import com.sadk.algorithm.bruteForce.CharListReadyFuncImpl;
import com.sadk.algorithm.bruteForce.FixValuePruner;
import com.sadk.algorithm.bruteForce.GoogleFunc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class GoogleFuncDemo {
    private static final Logger logger = LoggerFactory.getLogger(GoogleFuncDemo.class);

    private List<Long> callTimes = new ArrayList<Long>();

    /**
     * @param args
     */
    public static void main(String[] args) {
        final GoogleFuncDemo googleFuncDemo = new GoogleFuncDemo();
        for (int i = 0; i < 10; i++) {
            googleFuncDemo.test1();
        }
        googleFuncDemo.statistic();
    }

    private void statistic() {
        long sumTime = 0L;
        for (Long callTime : callTimes) {
            sumTime += callTime.longValue();
        }
        long avgTime = callTimes.size() > 0 ? sumTime / callTimes.size() : 0L;
        logger.info("avgTime={}ms", avgTime);
    }

    /**
     * ABA+CDA=CDDE  CDDE-CDA=ABA
     * B=9
     * E=6
     */
    private void test3() {
        final long startTime = System.currentTimeMillis();
        try {
            final GoogleFunc googleFunc = new GoogleFunc("CDDE", "CDA", "ABA", new FixValuePruner() {
                /**
                 * CDDE-CDA=ABA
                 * ci:
                 * {c=A, value=8, leading=true}
                 * {c=B, value=9, leading=false}
                 * {c=C, value=1, leading=true}
                 * {c=D, value=0, leading=false}
                 * {c=E, value=6, leading=false}
                 * 1006-108=898
                 * @param ci
                 * @param index
                 * @return
                 */
                @Override
                public boolean pruning(CharItem[] ci, int index) {
                    boolean result = true;
                    if (index < ci.length) {
                        final CharItem charItem = ci[index];
                        switch (index) {
                            case 1:
                                result = (charItem.value == -1) || (charItem.value == 9 && charItem.c == 'B');
                                break;
                            case 4:
                                result = (charItem.value == -1) || (charItem.value == 6 && charItem.c == 'E');
                                break;
                            default:
                                break;
                        }
                    }
                    return result;
                }
            });
            CharListReadyFunc callback = new CharListReadyFuncImpl();
            CharItem[] ci = new CharItem[]{
                    new CharItem('A', -1, true),
                    new CharItem('B', -1, false),
                    new CharItem('C', -1, true),
                    new CharItem('D', -1, false),
                    new CharItem('E', -1, false),
            };
            final boolean search = googleFunc.search(ci, 0, callback);
            logger.info("search={}", search);
        } finally {
            final long callTime = System.currentTimeMillis() - startTime;
            callTimes.add(callTime);
            logger.info("test3#callTime={}ms", callTime);
        }

    }

    /**
     * WWWDOT – GOOGLE = DOTCOM
     */
    private void test1() {
        final long startTime = System.currentTimeMillis();
        try {
            final GoogleFunc googleFunc = new GoogleFunc("WWWDOT", "GOOGLE", "DOTCOM", new FixValuePruner() {
                /**
                 * WWWDOT-GOOGLE=DOTCOM
                 * 777589-188103=589486
                 * 777589-188106=589483
                 * @param ci
                 * @param index
                 * @return
                 */
                @Override
                public boolean pruning(CharItem[] ci, int index) {
                    boolean result = true;
                    if (index < ci.length) {
                        final CharItem charItem = ci[index];
                        switch (index) {
                            case 0:
                                result = (charItem.value == -1) || (charItem.value == 7 && charItem.c == 'W');
                                break;
                            case 1:
                                result = (charItem.value == -1) || (charItem.value == 5 && charItem.c == 'D');
                                break;
                            case 2:
                                result = (charItem.value == -1) || (charItem.value == 8 && charItem.c == 'O');
                                break;
                            default:
                                break;
                        }
                    }
                    return result;
                }
            });
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
        } finally {
            final long callTime = System.currentTimeMillis() - startTime;
            callTimes.add(callTime);
            logger.debug("test1#callTime={}ms", callTime);
        }
    }

    /**
     * WWWDOT – GOOGLE = DOTCOM
     */
    private void test2() {
        final GoogleFunc googleFunc = new GoogleFunc("WWWDOT", "GOOGLE", "DOTCOM", new FixValuePruner() {
            @Override
            public boolean pruning(CharItem[] ci, int index) {
                return true;
            }
        });
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
