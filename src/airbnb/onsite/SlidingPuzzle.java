package airbnb.onsite;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {

    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return -1;
        }

        Set<String> visited = new HashSet<>();
        Queue<State> q = new LinkedList<>();

        int m = board.length;
        int n = board[0].length;

        // construct target
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < m * n - 1; i++) {
            t.append(i + 1);
        }
        t.append(0); // 0 在最后一个
        String target = t.toString();


        // find zero add to queue
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    q.offer(new State(board, new int[]{i, j}));
                }
            }
        }

        int[] del_x = {-1, 1, 0, 0};
        int[] del_y = {0, 0, 1, -1};

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                State cur = q.poll();

                int[][] curBoard = cur.board;
                int[] curZeroIdx = cur.zeroIndex;
                visited.add(boardToString(curBoard));

                if (target.equals(boardToString(curBoard))) {
                    return steps;
                }

                for (int j = 0; j < 4; j++) {
                    int next_x = curZeroIdx[0] + del_x[j];
                    int next_y = curZeroIdx[1] + del_y[j];

                    if (next_x >= 0 && next_x < m && next_y >= 0 && next_y < n) {
                        int[][] newBoard = swap(curBoard, curZeroIdx[0], curZeroIdx[1], next_x, next_y);
                        if (visited.contains(boardToString(newBoard))) {
                            continue;
                        }

                        q.offer(new State(newBoard, new int[]{next_x, next_y}));
                    }
                }

            }

            steps++;
        }

        return -1;
    }

    private String boardToString(int[][] curBoard) {
        StringBuilder sb = new StringBuilder();
        for (int[] line : curBoard) {
            for(int i : line) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    private int[][] swap(int[][] curBoard, int cur_x, int cur_y, int next_x, int next_y) {
        int m = curBoard.length;
        int n = curBoard[0].length;
        int[][] newBoard = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = curBoard[i][j];
            }
        }


        newBoard[cur_x][cur_y] = curBoard[next_x][next_y];
        newBoard[next_x][next_y] = curBoard[cur_x][cur_y];

        return newBoard;
    }


    class State {
        int[][] board;
        int[] zeroIndex;

        public State(int[][] board, int[] zeroIndex) {
            this.board = board;
            this.zeroIndex = zeroIndex;
        }
    }


    public static void main(String[] args) {
        SlidingPuzzle sp = new SlidingPuzzle();
        int[][] board = new int[][]{{1,2,3},{4,0,5}};

        System.out.println(sp.slidingPuzzle(board));
    }

}