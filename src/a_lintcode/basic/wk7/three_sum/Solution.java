package a_lintcode.basic.wk7.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * @param nums: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }

        //sort time: O (nlogn)
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1] ) {
                continue;
            }
            int target = -nums[i];
            findTwoSum(i, target, nums, res);
        }

        return res;
    }

    private void findTwoSum(int startIndex, int target, int[] nums, List<List<Integer>> res) {
        int left = startIndex + 1, right = nums.length - 1;

        while (left < right) {
            int curSum = nums[left] + nums[right];

            if (curSum == target) {
                List<Integer> list = new ArrayList<>();
                list.add(startIndex);
                list.add(left);
                list.add(right);
                res.add(list);

                left++;
                right--;

                while(left < right && nums[left] == nums[left - 1]) {
                    left++;
                }

                while(left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (curSum > target) {
                right--;
            } else { //curSum < target
                left++;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {};
    }
}
