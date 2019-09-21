package a_lintcode.basic.wk5.combinationsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        int[] nums = removeDup(candidates);

        List<Integer> subList = new ArrayList<>();
        bfs(0, target, nums, subList, result);

        return result;
    }


    void bfs(int pos, int target, int[] nums, List<Integer> subList, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(subList));
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            if (nums[i] > target) {
                break;
            }

            subList.add(nums[i]);
            bfs(i, target - nums[i], nums, subList, result);
            subList.remove(subList.size() -1);
        }
    }

    int[] removeDup(int[] candidates) {
        Arrays.sort(candidates);

        int index = 0 ;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[index] != candidates[i]) {
                candidates[++index] = candidates[i];
            }
        }

        int[] nums = new int[index + 1];
        for (int i = 0; i < index + 1; i++) {
            nums[i] = candidates[i];
        }

        return nums;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combinationSum(new int[]{2, 3, 6, 7}, 7);
        result.stream().forEach(System.out::println);
    }
}