package a_google;

import java.util.Arrays;

/**
 *
 * 左上到右下，怎么设计可玩性
 maze generation. 输入是int[][] board, int[] start, int[] dest,返回一个int[][] maze. 这题题意比较复杂。简单来说就是让你随机生成一个迷宫，
 条件是：
 （1）你肯定要生成一些墙，这些墙宽度为1，意思就是board[0][0] - board[0][3]可以是墙，s宽度为1， 长度为4。 但是不能生成board[0][0] - board[1][3]这样的厚墙（2*4)
 (2)  要求这个迷宫有且仅有一条路可以从start到达destination， 另外对于那些不是墙的blank cell，也要有可以从start到达它的路径。 也就是说不能有一些孤岛是不能到达的
 (3)  后来大哥给我简化了一点，如果输入board里面
 已经有一些墙， 用1表示，但是这个迷宫并不是具有通路的，然后让你根据以上条件，生成迷宫。
 思路：直接DFS每次走两步，避免生成没有墙的空地。
 https://en.wikipedia.org/wiki/Maze_generation_algorithm
 (Provider: Sean)


 Make the initial cell the current cell and mark it as visited
 While there are unvisited cells

    If the current cell has any neighbours which have not been visited
       Choose randomly one of the unvisited neighbours
       Push the current cell to the stack
       Remove the wall between the current cell and the chosen cell
        Make the chosen cell the current cell and mark it as visited
    Else if stack is not empty
       Pop a cell from the stack
       Make it the current cell

 后来还看到一个followup，要求生成的迷宫只有一条通路可以到目的地。

 我当时的思路是：从入口到出口生成一个不自相交path，这是唯一的一条可以到目的地的path。这条path把整个的四方地区分割成两个部分。
 因为要求只能有一条路，所以给一个门可以从path进入上下任意的部分，那么必然不能再给你一个门可以回到path。否则就有多条路了以到目的地了。所以从path的两边的wall里，各开一个门。
 然后分别进入门内用DFS来生成两部分的maze。并且不能打破path的墙。不知我对于“只有一条通路可以到目的地”的理解对否。
 */
public class GenerateRandomMaze {
    public int[][] maze(int n) {
        // Assumptions: n = 2 * k + 1, where k > = 0.
        int[][] maze = new int[n][n];
        // initialize the matrix as only (0,0) is corridor,
        // other cells are all walls at the beginning.
        // later we are trying to break the walls to form corridors.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    maze[i][j] = 0;
                } else {
                    maze[i][j] = 1;
                }
            }
        }

        System.out.println("before");
        for (int[] m : maze) {
            System.out.println(Arrays.toString(m));
        }

        generate(maze, 0, 0);


        System.out.println("after");
        for (int[] m : maze) {
            System.out.println(Arrays.toString(m));
        }

        return maze;


    }

    private void generate(int[][] maze, int x, int y) {
        // get a random shuffle of all the possible directions,
        // and follow the shuffled order to do DFS & backtrack.
        Dir[] dirs = Dir.values();
        shuffle(dirs);
        for (Dir dir: dirs) {
            // advance by two steps.
            int nextX = dir.moveX(x, 2);
            int nextY = dir.moveY(y, 2);
            if (isValidWall(maze, nextX, nextY)) {
                // only if the cell is a wall(meaning we have not visited before),
                // we break the walls through to make it corridors.
                maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0;
                maze[nextX][nextY] = 0;
                generate(maze, nextX, nextY);
            }
        }
    }

    // Get a random order of the directions.
    private void shuffle(Dir[] dirs) {
        for (int i = 0; i < dirs.length; i++) {
            int index = (int) (Math.random() * (dirs.length - i));
            Dir tmp = dirs[i];
            dirs[i] = dirs[i + index];
            dirs[i + index] = tmp;
        }
    }

    // check if the position (x,y) is within the maze and it is a wall.
    private boolean isValidWall(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
    }

    enum Dir {
        NORTH(-1, 0), SOUTH(1, 0), EAST(0, -1), WEST(0, 1);
        int deltaX;
        int deltaY;

        Dir(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        // move certain times of deltax.
        public int moveX(int x, int times) {
            return x + times * deltaX;
        }

        // move certain times of deltaY.
        public int moveY(int y, int times) {
            return y + times * deltaY;

        }
    }


    public static void main(String[] args) {
        GenerateRandomMaze solution = new GenerateRandomMaze();
        solution.maze(3);
    }
}