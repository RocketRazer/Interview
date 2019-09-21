import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int maxArea = 0;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, grid, visited);
                }
            }
        }

        return maxArea;
    }

    int[] del_x = {-1, 1, 0, 0};
    int[] del_y = {0, 0, 1, -1};

    private void bfs(int x, int y, int[][] grid, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{x, y});
        visited[x][y] = true;

        int count = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            System.out.println(cur[0] +"" + cur[1]);
            count++;
            System.out.println(count);
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + del_x[i];
                int ny = cur[1] + del_y[i];

                if (isValid(nx, ny, grid) && grid[nx][ny] == 1 && !visited[nx][ny]) {
                    q.offer(new int[]{ny, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        maxArea = Math.max(maxArea, count);
    }


    private boolean isValid(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] grid = {{1, 1, 1}};
        s.maxAreaOfIsland(grid);
    }
}