package com.yuerrd.leetcode;

import java.util.Arrays;

/**
 * @author yuerrd
 */
public class Solution189 {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[end];
            nums[end] = nums[start];
            nums[start] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Solution189 solution189 = new Solution189();
//        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        int[] ints = {-1};
        solution189.rotate(ints, 2);
        Arrays.stream(ints).forEach(System.out::print);
        System.out.println();

//        System.out.println(7 % 3);
    }
}
