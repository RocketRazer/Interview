package a_lintcode.improve.iw4.find_peak_element_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// O(n + m) 解法
// 在横竖两条中分线上找到最大值，这个最大值要么是peak要么两边有比他大的，
// 哪边比他大，下一个循环就去哪边 （根据这个最大值的横竖坐标比较），每次都能干掉 3/4 的元素，时间复杂度O(n)
public class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> find(int x1, int x2, int y1, int y2,
                              int[][] A) {

        int midRow = x1 + (x2 - x1) / 2;
        int midColumn = y1 + (y2 - y1) / 2;

        int x = midRow, y = midColumn;
        int max = A[midRow][midColumn];

        // fix Row, find the max from the columns
        for (int i = y1; i <= y2; ++i)
            if (A[midRow][i] > max) {
                max = A[midRow][i];
                x = midRow;
                y = i;
            }

        //fix column, find the max from the rows
        for (int i = x1; i <= x2; ++i)
            if (A[i][midColumn] > max) {
                max = A[i][midColumn];
                x = i;
                y = midColumn;
            }

        boolean isPeak = true;
        if (A[x - 1][y] > A[x][y]) {  //上
            isPeak = false;
            x -= 1;
        } else if (A[x + 1][y] > A[x][y]) { //下
            isPeak = false;
            x += 1;
        } else if (A[x][y - 1] > A[x][y]) { //左
            isPeak = false;
            y -= 1;
        } else if (A[x][y + 1] > A[x][y]) { //右
            isPeak = false;
            y += 1;
        }

        if (isPeak) {
            return new ArrayList<Integer>(Arrays.asList(x, y));
        }

        if (x >= x1 && x < midRow && y >= y1 && y < midColumn) {
            return find(x1, midRow - 1, y1, midColumn - 1, A);
        }

        if (x >= 1 && x < midRow && y > midColumn && y <= y2) {
            return find(x1, midRow - 1, midColumn + 1, y2, A);
        }

        if (x > midRow && x <= x2 && y >= y1 && y < midColumn) {
            return find(midRow + 1, x2, y1, midColumn - 1, A);
        }

        if (x >= midRow && x <= x2 && y > midColumn && y <= y2) {
            return find(midRow + 1, x2, midColumn + 1, y2, A);
        }

        return new ArrayList<Integer>(Arrays.asList(-1, -1));

    }

    public List<Integer> findPeakII(int[][] A) {
        // write your code here
        int n = A.length;
        int m = A[0].length;
        return find(1, n - 2, 1, m - 2, A);
    }

    public static void main(String[] args) {
        int[][] nums = new int[5][5];
        nums[0] = new int[]{1 ,2 ,3 ,6 ,5};
        nums[1] = new int[]{14,16,23,2,6};
        nums[2] = new int[]{13,17,19,21,7};
        nums[3] = new int[]{12,18,19,20,10};
        nums[4] = new int[]{10,14,11,10,9};

        Solution s = new Solution();
        System.out.print(s.findPeakII(nums));
    }
}
