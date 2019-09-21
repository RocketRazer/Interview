package a_lintcode.basic.wk7.sortColors;

import java.util.Arrays;

public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }


        int left = 0, right = nums.length -1;
        int runner = 0;

        while (runner <= right) {
            if (nums[runner] == 0) {
                swap(nums, left, runner);
                left++;
                runner++;
            } else if (nums[runner] == 2) {
                swap(nums, right, runner);
                right--;
            } else {
                runner++;
            }
        }

        return;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,0,0,1,2,0,2};
        s.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}