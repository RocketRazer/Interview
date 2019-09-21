package a_lintcode.dp.jump_game_ii;

public class Solution {
    /**
     * @param A: A list of integers
     * @return: A boolean
     */
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }

        //ways[i] 表示 minimum steps to jump to i
        int[] ways = new int[A.length];
        ways[0] = 0;
        for (int i = 1; i < ways.length; i++) {
            ways[i] = Integer.MAX_VALUE;
        }


        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (ways[j] != Integer.MAX_VALUE && A[j] + j >= i) {
                    ways[i] = Math.min(ways[i], ways[j] + 1);
                }
            }
        }

        return ways[A.length - 1];
    }

    public static void main(String[] args) {
        int[] A = {2,3,1,1,4};
        Solution s = new Solution();
        System.out.println(s.jump(A));
    }
}