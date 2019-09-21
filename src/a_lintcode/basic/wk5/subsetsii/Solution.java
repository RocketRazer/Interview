package a_lintcode.basic.wk5.subsetsii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        //validate input
        if (nums == null || nums.length == 0) {
            return result;
        }

        // why need sort? in order for submit to pass?
        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();

        dfs(0, list, nums, result);

        return result;
    }

    private void dfs(int pos, List<Integer> list, int[] nums, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));

        for (int i = pos; i < nums.length; i++) {
            // solution code is checking i != pos
            // i!= pos 保证了
            if (i != pos && nums[i] == nums[i - 1]) {
                continue;
            }

            list.add(nums[i]);
            dfs(i + 1, list, nums, result);
            list.remove(list.size() -1);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.subsetsWithDup(new int[]{1, 2, 2, 3});
        result.stream().forEach(System.out::println);
//        for(List<Integer> list : result) {
//            list.stream().forEach(System.out:: print);
//            System.out.println();
//        }
    }

}
