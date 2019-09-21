package airbnb.A;

import airbnb.onsite.SlidingPuzzle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ASliddingPuzzle {
    class Board {
        int[][] board;
        int zero_x;
        int zero_y;

        public Board(int[][] board, int zero_x, int zero_y) {
            this.board = board;
            this.zero_x = zero_x;
            this.zero_y = zero_y;
        }
    }

    public int slidingPuzzle(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m * n - 1; i++) {
            sb.append(i + 1);
        }
        sb.append(0);
        String target = sb.toString();


        //BFS
        Queue<Board> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    queue.offer(new Board(board, i, j));
                    break;
                }
            }
        }

        int[] del_x = {0,0,-1,1};
        int[] del_y = {1,-1,0,0};
        Set<String> visited = new HashSet<>();
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Board cur = queue.poll();
                String curBoardStr = boardToString(cur.board);
                visited.add(curBoardStr);

                if (target.equals(curBoardStr)) {
                    return steps;
                }



                for (int j = 0; j < 4; j++) {
                    int nx = cur.zero_x + del_x[j];
                    int ny = cur.zero_y + del_y[j];

                    if (nx >=0 && nx < m && ny >= 0 && ny < n) {
                        int[][] newBoard = swapBoard(cur, nx, ny);
                        String newBoardString = boardToString(newBoard);
                        if (visited.contains(newBoardString)) {
                            continue;
                        }
                        queue.offer(new Board(newBoard, nx, ny));
                    }
                }
            }

            steps++;
        }

        return  -1;
    }

    private int[][] swapBoard(Board cur, int nx, int ny) {
        int m = cur.board.length;
        int n = cur.board[0].length;

        int[][] newBoard = new int[m][n];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = cur.board[i][j];
            }
        }

        newBoard[cur.zero_x][cur.zero_y] = cur.board[nx][ny];
        newBoard[nx][ny] = 0;
        return newBoard;
    }

    private String boardToString(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ASliddingPuzzle sp = new ASliddingPuzzle();
        int[][] board = new int[][]{{1,2,3},{4,5,0}};

        System.out.println(sp.slidingPuzzle(board));
    }
}
