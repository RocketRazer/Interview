package a_lintcode.improve.surrouded_regions;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    public void surroundedRegions(char[][] board) {
        // write your code here
        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        // bfs first and last columns
        for (int i = 0; i < m; i ++) {
            bfs(new Node(i, 0), board);
            bfs(new Node(i, n-1), board);
        }

        //bfs first and last rows
        for (int j = 0; j < n; j++) {
            bfs(new Node(0, j), board);
            bfs(new Node(m-1, j), board);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'W') {
                    board[i][j] = 'O';
                }

                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void bfs(Node node, char[][] board) {
        if (board[node.x][node.y] != 'O') {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        board[node.x][node.y] = 'W';

        int[] delX = {-1, 1, 0, 0};
        int[] delY = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            for (int i = 0; i < 4; i++) {
                int nextX = x + delX[i];
                int nextY = y + delY[i];
                if (isValid(nextX, nextY, board) && board[nextX][nextY] == 'O') {
                    queue.offer(new Node(nextX, nextY));
                    board[nextX][nextY] = 'W';
                }
            }
        }
    }

    private boolean isValid(int x, int y, char[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }


    class Node {
        private int x;
        private int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        char[][] board = new char[4][4];
        board[0] = new char[]{'X', 'X', 'X', 'X'};
        board[1] = new char[]{'X', 'X', 'X', 'X'};
        board[2] = new char[]{'X', 'X', 'X', 'X'};
        board[3] = new char[]{'X', 'X', 'O', 'X'};

        s.surroundedRegions(board);
    }




}
