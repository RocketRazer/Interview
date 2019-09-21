package a_lintcode.basic.wk7.two_sum_unique;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum6(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // store <nums, used>
        Map<Integer, Boolean> map = new HashMap<>();

        int uniqueCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                if (map.get(target - nums[i]) == false) {
                    uniqueCount++;
                    map.put(target - nums[i], true);
                    map.put(nums[i], true);
                }
            } else {
                map.put(nums[i], false);
            }
        }

        return uniqueCount;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {11, 11, 11};
        System.out.println(s.twoSum6(nums, 22));
    }


}
