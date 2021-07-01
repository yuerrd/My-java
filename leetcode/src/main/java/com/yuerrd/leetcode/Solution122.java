package com.yuerrd.leetcode;

/**
 * @author yuerrd
 */
public class Solution122 {

    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(0, prices[i] - prices[i - 1]);
        }
        return result;
    }


    public static void main(String[] args) {
        Solution122 solution = new Solution122();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfit(prices));
    }
}
