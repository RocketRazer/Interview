package a_lintcode.basic.wk1.subsets;

import java.util.*;

public class Solution {

    /*
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        // List vs ArrayList （google）
        List<List<Integer>> results = new LinkedList<>();

        if (nums == null) {
            return results; // 空列表
        }

        Arrays.sort(nums);

        // BFS
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new LinkedList<Integer>());

        while (!queue.isEmpty()) {
            List<Integer> subset = queue.poll();
            results.add(subset);

            for (int i = 0; i < nums.length; i++) {
                if (subset.size() == 0 || subset.get(subset.size() - 1) < nums[i]) {
                    List<Integer> nextSubset = new LinkedList<Integer>(subset);
                    nextSubset.add(nums[i]);
                    queue.offer(nextSubset);
                }
            }
        }

        return results;
    }

    public List<List<Integer>> subsets_bit(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = nums.length;
        Arrays.sort(nums);

        // 1 << n is 2^n
        // each subset equals to an binary integer between 0 .. 2^n - 1
        // 0 -> 000 -> []
        // 1 -> 001 -> [1]
        // 2 -> 010 -> [2]
        // 3 -> 011 -> [2, 1]
        // 4 -> 100 -> [3]
        // 5 -> 101 -> [2, 1]
        // 6 -> 110 -> [3, 2]
        // 7 -> 111 -> [1,2,3]

        // 循环每一个二进制数， check每一位上是select还是不select
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<Integer>();


            for (int j = 0; j < n; j++) {
                // check whether the jth digit in i's binary representation is 1
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        return result;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>>  res = s.subsets_bit(new int[]{1, 2, 3});
        System.out.println(res);
    }
}