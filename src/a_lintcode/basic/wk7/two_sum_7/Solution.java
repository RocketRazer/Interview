package a_lintcode.basic.wk7.two_sum_7;

import java.util.Arrays;

public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++){
            int left = i, right = left + 1;
            while (right < nums.length) {
                int diff = nums[right] - nums[left];
                if (diff == target) {
                    res[0] = left + 1;
                    res[1] = right + 1;
                    break;
                } else if (diff < target) {
                    right++;
                } else {
                    break;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,0,1};
        System.out.println(s.twoSum7(nums, 0));
    }
}