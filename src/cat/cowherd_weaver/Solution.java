package cat.cowherd_weaver;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 *
 1751. Cowherd&Weaver
 cat-only-icon
 CAT Only
 中文English
 On the Qixi Festival, the Cowherd and the Weaver play together in a maze size of n*m . However, they get separated from each other. Now given the maze consisting of .,*,S,T, where . denotes an empty space, * denotes an obstacle, S denotes the position of the cowherd, T denotes the position of the weaver, the Cowherd and the Weaver will try to find each other(they can move to the grid up or down or left or right or stand still, but they can't walk outside of the maze or move to obstacles).Is it possible for them reunion? If possible, return True, otherwise return False.

 Example
 Example 1:

 Input:
 [
 "S..*",
 "*.**",
 "...T"
 ]
 Output: true
 Explanation:
 weaver don't need to move
 Cowherd's route:(0,0)->(0,1)->(1,1)->(2,1)->(2,2)->(2,3)
 Example 2:

 Input:
 [
 "S..*",
 "***.",
 "...T"
 ]
 Output: false
 Explanation
 It is impossible for them to reunion
 Notice
 2<=n,m<=1000
 */
public class Solution {
    /**
     * @param maze: the maze
     * @return: Can they reunion?
     */
    public boolean findHer(String[] maze) {
        if (maze == null || maze.length == 0 || maze[0].length() == 0) {
            return false;
        }



        int m = maze.length;
        int n = maze[0].length();

        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            grid[i] = maze[i].toCharArray();
        }


        Node S = null, T = null;

        //find S & T
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    S = new Node(i, j);
                } else if (grid[i][j] == 'T') {
                    T = new Node(i, j);
                }
            }
        }


        Queue<Node> queue = new LinkedList<>();
        queue.add(S);
        int[] delX = {-1, 1, 0, 0};
        int[] delY = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nextX = cur.x + delX[k];
                int nextY = cur.y + delY[k];

                if (nextX == T.x && nextY == T.y) {
                    return true;
                }
                if (isInbound(nextX, nextY, m, n) && grid[nextX][nextY] == '.') {
                    queue.add(new Node(nextX, nextY));
                    grid[nextX][nextY] = '*';
                }
            }
        }

        return false;

    }

    private boolean isInbound(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] maze = {"S..*",
                "*.**",
                "...T"};
        System.out.println(s.findHer(maze));
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}