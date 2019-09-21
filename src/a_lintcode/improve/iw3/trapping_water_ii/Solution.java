package a_lintcode.improve.iw3.trapping_water_ii;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    class Cell {
        int x, y, h;
        public Cell (int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    class CellComparator implements Comparator<Cell> {
        public int compare(Cell a, Cell b) {
            return a.h - b.h;
        }
    }

    private int[] delX = {-1, 1, 0, 0};
    private int[] delY = {0, 0, 1, -1};
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }

        PriorityQueue<Cell> minHeap = new PriorityQueue<>(new CellComparator());

        int m = heights.length, n = heights[0].length;

        // track which cell has been visited
        boolean[][] visited = new boolean[m][n];

//        for (int i = 0; i < m; i++) {
//            minHeap.offer(new Cell(i, 0, heights[i][0]));
//            visited[i][0] = true;
//            minHeap.offer(new Cell(i, n-1, heights[i][n-1]));
//            visited[i][n-1] = true;
//        }
//
//        //add the first row and last row into the minHeap
//        for (int j = 0; j < n; j++) {
//            minHeap.offer(new Cell(0, j, heights[0][j]));
//            visited[0][j] = true;
//            minHeap.offer(new Cell(m-1, j, heights[m-1][j]));
//            visited[m-1][j] = true;
//        }


        // add the first column and last column into the minHeap
        for (int i = 0; i < m; i++) {
            addCellToHeap(i, 0, minHeap, visited, heights);
            addCellToHeap(i, n-1, minHeap, visited, heights);
        }

        //add the first row and last row into the minHeap
        for (int j = 0; j < n; j++) {
            addCellToHeap(0, j, minHeap, visited, heights);
            addCellToHeap(m-1, j, minHeap, visited, heights);
        }

        int ans = 0;
        while (!minHeap.isEmpty()) {
            Cell cur = minHeap.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + delX[i];
                int nextY = cur.y + delY[i];
                if (isValid(nextX, nextY, m, n) && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    minHeap.offer(new Cell(nextX, nextY, Math.max(heights[nextX][nextY], cur.h)));
                    ans += Math.max(0, cur.h - heights[nextX][nextY]);
                }
            }
        }

        return ans;
    }

    private void addCellToHeap (int x, int y, PriorityQueue<Cell> minHeap, boolean[][] visited, int[][] heights) {
        if (!visited[x][y]) {
            visited[x][y] = true;
            minHeap.offer(new Cell(x,y,  heights[x][y]));
        }
    }

    private boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public  static void main(String[] args) {
        Solution s = new Solution();
        int[][] heights = new int[5][4];
        heights[0] = new int[]{12,13,0,12};
        heights[1] = new int[]{13,4,13,12};
        heights[2] = new int[]{13,8,10,12};
        heights[3] = new int[]{12,13,12,12};
        heights[4] = new int[]{13,13,13,13};

        System.out.println(s.trapRainWater(heights));
    }

}