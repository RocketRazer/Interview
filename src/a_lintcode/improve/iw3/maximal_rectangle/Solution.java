package a_lintcode.improve.iw3.maximal_rectangle;

import java.util.Stack;

public class Solution {
    /**
     * @param matrix: a boolean 2D matrix
     * @return: an integer
     */
    public int maximalRectangle(int[][] matrix) {
        int maxRectangle = 0;
        if (matrix == null || matrix[0].length == 0) {
            return maxRectangle;
        }

        int[] preHeights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int[] curHeights = new int[matrix[0].length];
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    curHeights[j] = preHeights[j] + 1;
                }
            }
            preHeights = curHeights;
            maxRectangle = Math.max(maxRectangle, largestRectangleInHistogram(curHeights));
        }

        return maxRectangle;
    }

    private int largestRectangleInHistogram(int heights[]) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int max = 0;
        //store heights index
        Stack<Integer> stack = new Stack<>();
        for (int i =0; i <= heights.length; i++) {
            int curHeight = i == heights.length ? -1 : heights[i];
            while (!stack.isEmpty() && curHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() -1;
                max = Math.max(max, h*w);
            }
            stack.push(i);
        }

        return max;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[5][5];
        matrix[0] = new int[]{1, 1, 0, 0, 1};
        matrix[1] = new int[]{0, 1, 0, 0, 1};
        matrix[2] = new int[]{0, 0, 1, 1, 1};
        matrix[3] = new int[]{0, 0, 1, 1, 1};
        matrix[4] = new int[]{0, 0, 0, 0, 1};
        s.maximalRectangle(matrix);
    }
}