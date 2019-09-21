package airbnb.onsite;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzlePractice {

    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return -1;
        }

        int m = board.length;
        int n = board[0].length;



        // build target
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m*n -1; i++) {
            sb.append(i+1);
        }
        sb.append(0);
        String target = sb.toString();


        // conver board to string and check for visited
        Set<String> visited = new HashSet<>();

        //find init 0 state and add to the queue
        Queue<State> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j =0; j < n; j++) {
                if (board[i][j] == 0) {
                    State initState = new State(board, new int[]{i, j});
                    q.offer(initState);
                }
            }
        }


        //bfs
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i =0; i < size; i++) {
                State cur = q.poll();

                String curString = boardToString(cur);
                visited.add(curString);

                if (target.equals(curString)) {
                    return steps;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = cur.zeroIndex[0] + del_x[j];
                    int ny = cur.zeroIndex[1] + del_y[j];

                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        int[][] newBoard = swap(cur.board, nx, ny, cur.zeroIndex[0], cur.zeroIndex[1]);
                        State nextState = new State(newBoard, new int[]{nx, ny});
                        if (!visited.contains(boardToString(nextState))) {
                            q.offer(nextState);
                        }
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private int[][] swap(int[][] curBoard, int nx, int ny, int curZeroX, int curZeroY) {
        int m = curBoard.length;
        int n = curBoard[0].length;
        int[][] newBoard = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = curBoard[i][j];
            }
        }


        newBoard[curZeroX][curZeroY] = curBoard[nx][ny];
        newBoard[nx][ny] = curBoard[curZeroX][curZeroY];

        return newBoard;

    }

    int[] del_x = {-1, 1, 0, 0};
    int[] del_y = {0, 0, 1, -1};

    private String boardToString(State cur) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cur.board.length; i++) {
            for (int j =0; j < cur.board[0].length; j++) {
                sb.append(cur.board[i][j]);
            }
        }

        return sb.toString();
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
        SlidingPuzzlePractice sp = new SlidingPuzzlePractice();
        int[][] board = new int[][]{{1,2,3},{4,0,5}};

        System.out.println(sp.slidingPuzzle(board));
    }
}
