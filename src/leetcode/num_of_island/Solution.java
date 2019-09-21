package leetcode.num_of_island;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Solution {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int res = 0;

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }

                if (grid[i][j] == '1') {
                    changeAdj(i, j, grid, visited);
                }
            }
        }

        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }

                if (grid[i][j] == '1') {
                    dfs(i, j, grid, visited);
                    res++;
                }
            }
        }

        return res;
    }

    int[] delX = {0, 0, 1, -1};
    int[] delY = {1, -1, 0, 0};

    private boolean isInbound(int x, int y, char[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    private void dfs(int x, int y, char[][] grid, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0 ; i < 4; i++) {
            int next_x = x + delX[i];
            int next_y = y + delY[i];

            if (isInbound(next_x, next_y, grid)
                    && !visited[next_x][next_y]
                    && (grid[next_x][next_y] == '1' || grid[next_x][next_y] == '2') ) {
                dfs(next_x, next_y, grid, visited);
            }
        }
    }

    private void changeAdj(int x, int y, char[][] grid, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0 ; i < 4; i++) {
            int next_x = x + delX[i];
            int next_y = y + delY[i];


            if (isInbound(next_x, next_y, grid)) {
                if (grid[next_x][next_y] == '0')  {
                    grid[next_x][next_y] = '2';
                } else if (grid[next_x][next_y] == '1' && !visited[next_x][next_y]) {
                    changeAdj(next_x, next_y, grid, visited);
                }

            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] gird = new char[4][5];
        gird[0] = "11111".toCharArray();
        gird[1] = "10000".toCharArray();
        gird[2] = "10000".toCharArray();
        gird[3] = "00001".toCharArray();

        System.out.println(s.numIslands(gird));

        for (char[] c : gird) {
            System.out.println(Arrays.toString(c));
        }
    }
}


class UnionFind {
    private int[] father = null;
    private int count;

    public UnionFind(int count, int total) {
        father = new int[total + 1];
        for (int i = 0; i <= total; i++) {
            father[i] = i;
        }

        this.count = count;
    }

    public int find (int x) {
        if (father[x] == x) {
            return father[x];
        }

        return father[x] = find(father[x]);
    }

    public void connect( int a, int b) {
        int root_a = find(a);
        int root_b = find(b);

        if (root_a != root_b) {
            father[root_a] = root_b;
            count--;
        }
    }

    public int query() {
        return count;
    }

    public void increaseCount() {
        count++;
    }
}




