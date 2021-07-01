package com.yuerrd.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuerrd
 */
public class Solution15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            int curr = nums[i];
            if (curr > 0) break;
            if (i > 0 && curr == nums[i - 1]) continue;

            int start = i + 1;
            int end = length - 1;
            while (start < end) {
                int sum = curr + nums[start] + nums[end];
                if (sum == 0) {
                    results.add(Arrays.asList(curr, nums[start], nums[end]));
                    while (start < end && nums[start] == nums[start + 1]) {
                        start++;
                    }
                    while (start < end && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    start++;
                    end--;
                } else if (sum > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        int[] nums = {1, -1, -1, 0};
        solution15.threeSum(nums);
    }
}
