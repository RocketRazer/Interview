package a_lintcode.basic.wk7.two_sum_less_than_equal_to;

import java.util.Arrays;

public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length < 2) {
            return 0;
        }

        Arrays.sort(nums);
        int left = 0, right = nums.length -1;
        int total = 0;
        while (left < right) {


            int curSum = nums[left] + nums[right];

            if (curSum <= target) {
                total += right - left;
                left++;
                right--;
                while (left < right && nums[left - 1] == nums[left]) {
                    right--;
                }
            } else {
                right--;

                while (left < right && nums[right + 1] == nums[right]) {
                    right--;
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1, 1, 1, 2, 2, 2}; // unqiue should be 3 pairs

        System.out.println(s.twoSum5(nums, 4));
    }
}
