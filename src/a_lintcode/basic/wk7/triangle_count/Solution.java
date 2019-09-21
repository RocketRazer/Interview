package a_lintcode.basic.wk7.triangle_count;

import java.util.Arrays;

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);

        int count = 0;
        for (int i = 0 ; i < nums.length - 2; i ++) {
            int right = i + 2;
            for (int left = i + 1; left < nums.length - 1; left++) {
                if (right == left) {
                    right++;
                }

                while (right < nums.length && nums[right] - nums[left] < nums[i]) {
                    count++;
                    right++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {4, 4, 4, 4};
        Solution s = new Solution();
        System.out.println(s.triangleCount(nums));
    }
}