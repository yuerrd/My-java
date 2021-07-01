package com.yuerrd.leetcode;

/**
 * @author yuerrd
 */
public class Solution27 {


    public int removeElement(int[] nums, int val) {
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[end++] = nums[i];
            }
        }
        return end;
    }

    public static void main(String[] args) {

        Solution27 solution27 = new Solution27();
        int[] nums = {3, 2, 2, 3};
        System.out.println(solution27.removeElement(nums, 3));
    }
}
