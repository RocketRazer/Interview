package a_lintcode.improve.iw1.kth_smallest_in_matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};

        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(k, new PairComparator());
        Pair pair = new Pair(0, 0, matrix[0][0]);
        visited[0][0] = true;
        minHeap.add(pair);

        for (int i  = 0; i < k - 1; i++) {
            Pair cur = minHeap.poll();
            for (int j = 0; j < 2; j++) {
                int nextX = cur.x + dx[j];
                int nextY = cur.y + dy[j];
                if (nextX < m && nextY < n && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    minHeap.add(new Pair(nextX, nextY, matrix[nextX][nextY]));
                }
            }
        }

        return minHeap.peek().val;
    }

    class Pair {
        int x;
        int y;
        int val;

        public Pair (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    class PairComparator implements Comparator<Pair> {
        public int compare (Pair x, Pair y) {
            return x.val - y.val;
        }
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[3][3];
        //[[1,5,7],[3,7,8],[4,8,9]]
        matrix[0] = new int[]{1,5,7};
        matrix[1] = new int[]{3,7,8};
        matrix[2] = new int[]{4,8,9};
        s.kthSmallest(matrix, 4);
    }
}