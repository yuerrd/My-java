package com.yuerrd.leetcode;

/**
 * @author yuerrd
 */
public class Solution53 {

    public static int maxSubArray(int[] nums) {
        int pre = 0, max = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            max = Math.max(max, pre);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = {-100000};
        System.out.println(maxSubArray(nums));
    }
}
