package dfs.combination_sum;

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
        this.res = new ArrayList<>();
        this.candidates = candidates;
        this.target = target;


        Arrays.sort(candidates);

        List<Integer> list = new ArrayList<>();

        dfs(0, list, 0);

        return res;
    }

    List<List<Integer>> res;
    int target;
    int[] candidates;

    private void dfs(int index, List<Integer> list, int sum) {
        if (index == candidates.length) {
            if (sum == target) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        if (sum > target) {
            return;
        }

        dfs(index + 1, list, sum);

        if (index > 0 && candidates[index] == candidates[index-1]) {
            return;
        }

        list.add(candidates[index]);
        dfs(index + 1, list, sum + candidates[index]);
        list.remove(list.size() - 1);
    }


    public static void main(String[] args) {
        int[] a = {2,3,6,7};
        Solution s = new Solution();
        s.combinationSum(a, 7);

    }
}
