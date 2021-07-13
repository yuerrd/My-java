package com.yuerrd.leetcode;

/**
 * @author yuerrd
 */
public class Solution198 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[length - 1];
    }


    public static void main(String[] args) {
        Solution198 solution198 = new Solution198();
//        int[] nums = {1, 2, 3, 1};
//        int[] nums = {2, 7, 9, 3, 1};
        int[] nums = {2};
        System.out.println(solution198.rob(nums));
    }
}
