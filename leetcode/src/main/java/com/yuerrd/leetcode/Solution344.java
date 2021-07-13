package com.yuerrd.leetcode;

/**
 * @author yuerrd
 */
public class Solution344 {

    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        Solution344 solution344 = new Solution344();
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        solution344.reverseString(s);
    }
}
