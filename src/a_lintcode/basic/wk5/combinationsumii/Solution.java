package a_lintcode.basic.wk5.combinationsumii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * @param nums: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        List<Integer> subList = new ArrayList<>();

        bfs(0, target, subList, result, nums);

        return result;
    }


    void bfs(int pos, int target, List<Integer> subList, List<List<Integer>> result, int[] nums) {
        if (target == 0) {
            result.add(new ArrayList<>(subList));
        }

        for (int i = pos; i < nums.length;  i++) {
            if (nums[i] > target) {
                break;
            }

            if (i != pos && nums[i] == nums[i - 1]) {
                continue;
            }

            subList.add(nums[i]);
            bfs(i + 1, target - nums[i], subList, result, nums);
            subList.remove(subList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combinationSum2(new int[]{10,1,6,7,2,1,5}, 8);
        result.stream().forEach(System.out::println);
    }
}


