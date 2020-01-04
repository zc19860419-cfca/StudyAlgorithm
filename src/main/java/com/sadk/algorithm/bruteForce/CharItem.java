package com.sadk.algorithm.bruteForce;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class CharItem {
    public char c;
    public int value;
    public boolean leading;

    public CharItem(char c, int value, boolean leading) {
        this.c = c;
        this.value = value;
        this.leading = leading;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CharItem{");
        sb.append("c=").append(c);
        sb.append(", value=").append(value);
        sb.append(", leading=").append(leading);
        sb.append('}');
        return sb.toString();
    }
}
