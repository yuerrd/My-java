package com.yuerrd.leetcode;

/**
 * @author yuerrd
 */
public class Solution66 {


    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] = digits[digits.length - 1] + 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if ((digits[i]) < 10) {
                break;
            } else {
                digits[i] = 0;
                if (i == 0) {
                    int[] ints = new int[digits.length + 1];
                    ints[0] = 1;
                    return ints;
                }
                digits[i - 1] = digits[i - 1] + 1;
            }
        }
        return digits;
    }

    public static void main(String[] args) {
        Solution66 solution66 = new Solution66();
        int[] digits ={9};
        solution66.plusOne(digits);
    }
}
