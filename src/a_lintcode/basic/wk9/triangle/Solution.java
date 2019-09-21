package a_lintcode.basic.wk9.triangle;

public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        //initialize the hash
        int n = triangle.length;
        hash = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hash[i][j] = Integer.MAX_VALUE;
            }
        }

        return divideAndConquer(0, 0, triangle);
    }

    //Memorization
    private int[][] hash;


    //return the minimum sum from (x, y) to bottom
    private int divideAndConquer(int x, int y, int[][] triangle) {
        //exit
        if (x >= triangle.length) {
            return 0;
        }

        if (hash[x][y] == Integer.MAX_VALUE) {
            int minDown = divideAndConquer(x+1, y, triangle);
            int minRightDown = divideAndConquer(x+1, y+1, triangle);
            hash[x][y] = triangle[x][y] + Math.min(minDown, minRightDown);
        }

        return hash[x][y];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] nums = new int[1][1];
        nums[0][0] = -10;
        s.minimumTotal(nums);
    }
}