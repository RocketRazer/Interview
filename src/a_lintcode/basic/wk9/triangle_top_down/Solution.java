package a_lintcode.basic.wk9.triangle_top_down;

public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        int n = triangle.length;

        // 表示从(0,0)出发走到(i,j)最小路径和
        int[][] minSum = new int[n][n];

        minSum[0][0] = triangle[0][0];

        //initialize 最左边一列和最右边一列
        for (int i = 1; i < n; i++) {
            minSum[i][0] = minSum[i-1][0] + triangle[i][0]; //最左边一列
            minSum[i][i] = minSum[i-1][i-1] + triangle[i][i]; //最右边一列
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < triangle[i].length; j++) {
                minSum[i][j] = triangle[i][j] + Math.min(minSum[i-1][j-1], minSum[i-1][j]);
            }
        }

        int minPathSum = Integer.MAX_VALUE;
        for (Integer sum : minSum[n-1]) {
            if (minPathSum > sum) {
                minPathSum = sum;
            }
        }

        return minPathSum;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] triangle = new int[3][3];
        triangle[0] = new int[]{-1};
        triangle[1] = new int[]{2, 3};
        triangle[2] = new int[]{1, -1, -3};
        s.minimumTotal(triangle);
    }
}
