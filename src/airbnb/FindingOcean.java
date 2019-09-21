package airbnb;

import java.util.LinkedList;
import java.util.Queue;

public class FindingOcean {
    /**
     * Given: An array of strings where L indicates land and W indicates water,
     * and a coordinate marking a starting point in the middle of the ocean.
     *
     * Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os.
     *
     *  An ocean coordinate is defined to be the initial coordinate if a W, and
     * any coordinate directly adjacent to any other ocean coordinate.
     * void findOcean(String[] map, int row, int column);
     *
     *
     * String[] map = new String[]{
     * "WWWLLLW",
     * "WWLLLWW",
     * "WLLLLWW"};
     *
     * printMap(map);
     *
     * STDOUT:
     * WWWLLLW
     * WWLLLWW
     * WLLLLWW
     *
     * findOcean(map, 0, 1);
     * printMap(map);
     *
     * STDOUT:
     * OOOLLLW
     * OOLLLWW
     * OLLLLWW
     */

    public void floodFill(char[][] board, int i, int j, char oldColor, char newColor) {
        if (board[i][j] != oldColor || board[i][j] == newColor) { return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i * board[0].length + j);
        board[i][j] = newColor;

        while (!queue.isEmpty()) {
            int pos = queue.poll();
            int x = pos / board[0].length;
            int y = pos % board[0].length;

            if (x + 1 < board.length && board[x + 1][y] == oldColor) {
                queue.add((x + 1) * board[0].length + y);
                board[x + 1][y] = newColor;
            }

            if (x - 1 >= 0 && board[x - 1][y] == oldColor) {
                queue.add((x - 1) * board[0].length + y);
                board[x - 1][y] = newColor;
            }

            if (y + 1 < board[0].length && board[x][y + 1] == oldColor) {
                queue.add(x * board[0].length + y + 1);
                board[x][y + 1] = newColor;
            }

            if (y - 1 >= 0 && board[x][y - 1] == oldColor) {
                queue.add(x * board[0].length + y - 1);
                board[x][y - 1] = newColor;
            }
        }
    }
}
