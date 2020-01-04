package com.sadk.algorithm.editDistance;

import cfca.org.slf4j.Logger;
import cfca.org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class EditDistance {
    private static final Logger logger = LoggerFactory.getLogger(EditDistance.class);

    public static int distance(String src, String dest) {
        int result = -1;
        if (StringUtils.isEmpty(src) || StringUtils.isEmpty(dest)) {
            result = Math.abs(src.length() - dest.length());
            logger.info("{},{}={}", src, dest, result);
        } else {
            if (src.charAt(0) == dest.charAt(0)) {
                result = distance(src.substring(1), dest.substring(1));
                logger.info("{},{}={}", src, dest, result);
            } else {
                logger.info("==================");
                //插入一个字符
                final int edInsert = distance(src, dest.substring(1)) + 1;
                //删除一个字符
                final int edDelete = distance(src.substring(1), dest) + 1;
                //替代一个字符
                final int edReplace = distance(src.substring(1), dest.substring(1)) + 1;

                result = Math.min(edReplace, Math.min(edInsert, edDelete));
                logger.info("{},{}={}", src, dest, result);
                logger.info("==================");
            }
        }

        return result;
    }

    public static int distance1(String src, String dest) {
        int result = -1;
        if (StringUtils.isEmpty(src) || StringUtils.isEmpty(dest)) {
            result = Math.abs(src.length() - dest.length());
            logger.info("{},{}={}", src, dest, result);
        } else {
            int maxLen = Math.max(src.length(), dest.length()) + 1;

            int[][] d = new int[maxLen][maxLen];
            for (int i = 0; i <= src.length(); i++) {
                for (int j = 0; j <= dest.length(); j++) {
                    d[i][j] = -1;
                }
            }

            for (int i = 0; i <= src.length(); i++) {
                d[i][0] = i;
            }

            for (int j = 0; j <= dest.length(); j++) {
                d[0][j] = j;
            }

            int edInsert, edDelete, edReplace;
            for (int i = 1; i <= src.length(); i++) {
                for (int j = 1; j <= dest.length(); j++) {
                    if (src.charAt(i - 1) == dest.charAt(j - 1)) {
                        d[i][j] = d[i - 1][j - 1];
                    } else {
                        //插入一个字符
                        edInsert = d[i][j - 1] + 1;
                        //删除一个字符
                        edDelete = d[i - 1][j] + 1;
                        //替代一个字符
                        edReplace = d[i - 1][j - 1] + 1;
                        d[i][j] = Math.min(edReplace, Math.min(edInsert, edDelete));
                    }
                }
            }
            result = d[src.length()][dest.length()];
        }


        return result;
    }
}
