package a_lintcode.basic.wk9.knight_shortest_paths_ii;

public class Solution {
    /**
     * @param grid: a chessboard included 0 and 1
     * @return: the shortest path
     */
    public int shortestPath2(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 ) {
            return -1;
        }

        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == true || grid[m-1][n-1] == true) {
            return -1;
        }

        //from (0, 0) to (x, y) shortest path
        int[][] path = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                path[i][j] = Integer.MAX_VALUE;
            }
        }

        path[0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == false) {
                    int min = Integer.MAX_VALUE;
                    for (int del = 0; del < 4; del++) {
                        int x = i + deltaX[del];
                        int y = j + deltaY[del];
                        if (isValid(x, y, grid)) {
                            if (path[x][y] < min) {
                                min = path[x][y];
                            }
                        }
                    }

                    if (min < Integer.MAX_VALUE) {
                        path[i][j] = min + 1;
                    }
                }
            }
        }

        if (path[m-1][n-1] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return path[m-1][n-1];
        }
    }

    private int[] deltaX = new int[]{-1, 1, -2, 2};
    private int[] deltaY = new int[]{-2, -2, -1, -1};

    private boolean isValid(int x, int y, boolean[][] grid) {
        return (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        boolean[][] grid = new boolean[3][4];
        s.shortestPath2(grid);
    }
}