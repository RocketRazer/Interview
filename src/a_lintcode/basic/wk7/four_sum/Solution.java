package a_lintcode.basic.wk7.four_sum;

import java.util.*;

public class Solution {
    /**
     * @param nums: Give an array
     * @param target: An integer
     * @return: Find all unique quadruplets in the array which gives the sum of zero
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }

        // Time: O (nlogn)
        Arrays.sort(nums);

        //Time: O (n^3)
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i-1]){
                continue;
            }

            for (int j = i + 1; j < (nums.length - 2); j++) {
                if (j != i+1 && nums[j] == nums[j-1]) {
                    continue;
                }

                int left = j + 1, right = nums.length -1;
                while (left < right) {
                    int curSum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (curSum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(j);
                        list.add(left);
                        list.add(right);
                        res.add(list);

                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }

                        while(left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (curSum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return res;
    }
}