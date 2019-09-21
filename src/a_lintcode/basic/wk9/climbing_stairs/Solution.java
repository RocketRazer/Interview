package a_lintcode.basic.wk9.climbing_stairs;

public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        // ways[n]: ways to climbing to stairs n
        int[] ways = new int[n + 1];
        ways[0] = 1;
        ways[1] = 1;

        for (int i = 2; i <= n; i++) {
            ways[n] = ways[n-1] + ways[n-2];
        }

        return ways[n];
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.climbStairs(3);
    }
}