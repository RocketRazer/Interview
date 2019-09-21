package a_lintcode.basic.wk1.subsets_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> res;
    private int[] nums;
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.res = new ArrayList<>();

        if (nums == null) {
            return res;
        }

        Arrays.sort(nums);
        this.nums = nums;

        dfs(0, new ArrayList<Integer>());

        return res;
    }

    private void dfs(int startIndex, List<Integer> list) {
        res.add(new ArrayList<>(list));

        for (int i = startIndex; i < nums.length; i++) {

            if (i != startIndex && nums[i] == nums[i-1]) {
                continue;
            }

            list.add(nums[i]);
            dfs(i+1, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.subsetsWithDup(new int[]{1, 2, 2});
    }
}