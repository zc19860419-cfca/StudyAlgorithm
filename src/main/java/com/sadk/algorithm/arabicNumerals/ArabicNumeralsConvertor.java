package com.sadk.algorithm.arabicNumerals;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class ArabicNumeralsConvertor {
    private static final int CHN_NUM_CHAR_COUNT = 10;
    private static final String[] chnNumChar = new String[]{
            "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
    };

    private static final String[] chnUnitSection = new String[]{
            "", "万", "亿", "万亿"
    };

    private static final String[] chnUnitChar = new String[]{
            "", "十", "百", "千"
    };

    /**
     * |千   百   十  ''|[亿] |千   百   十  ''|[万] |千   百   十  ''|['']
     * | ---section--- |    | ---section--- |     | ---section--- |
     *
     * @param num
     * @return
     */
    public String convert(int num) {
        int unitPos = 0;
        StringBuilder builder = new StringBuilder(32);
        boolean needZero = false;
        int section;
        String currentSection;
        //从低位到高位遍历
        while (num > 0) {
            //取出当前section
            section = num % 10000;
            if (needZero) {
                builder.insert(0, chnNumChar[0]);
            }

            currentSection = convertSection(section);
            currentSection += (section != 0 ? chnUnitSection[unitPos] : chnUnitSection[0]);
            builder.insert(0, currentSection);
            //是否不足 1000,那么需要在下一个 section 之间补零
            needZero = (section < 1000 && section > 0);
            num = num / 10000;
            unitPos++;
        }

        return builder.toString();
    }

    private String convertSection(int section) {
        int unitPos = 0;
        int v = 0;
        boolean needFillZero = true;
        StringBuilder builder = new StringBuilder(32);
        String currentNumber;
        while (section > 0) {
            v= section%10;
            if (v==0){
                if(0 == section || !needFillZero){
                    needFillZero = true;
                    builder.insert(0, chnNumChar[v]);
                }
            }else {
                needFillZero = false;
                currentNumber = chnNumChar[v];
                currentNumber+=chnUnitChar[unitPos];
                builder.insert(0, currentNumber);
            }
            unitPos++;
            section=section/10;
        }
        return builder.toString();
    }
}
