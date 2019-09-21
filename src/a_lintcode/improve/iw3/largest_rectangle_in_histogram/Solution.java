package a_lintcode.improve.iw3.largest_rectangle_in_histogram;

import java.util.Stack;

public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= height.length; i++) {
            int curHeight = i == height.length ? -1 : height[i];
            while (!stack.isEmpty() && curHeight < height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() -1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }

        return max;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] height = new int[]{2,1,5,6,2,3};
        s.largestRectangleArea(height);
    }
}
