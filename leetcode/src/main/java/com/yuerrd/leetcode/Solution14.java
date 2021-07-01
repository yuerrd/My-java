package com.yuerrd.leetcode;


/**
 * @author yuerrd
 */
public class Solution14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            minLength = Math.min(strs[i].length(), minLength);
        }
        int end = 0;
        forEnd:
        for (int i = 0; i < minLength; i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[0].charAt(i) != strs[j].charAt(i)) {
                    break forEnd;
                }
            }
            end++;
        }
        return strs[0].substring(0, end);
    }

    public static void main(String[] args) {
        Solution14 solution14 = new Solution14();
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(solution14.longestCommonPrefix(strs));
    }
}
