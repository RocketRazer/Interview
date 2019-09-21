package a_lintcode.basic.wk2.search_insert;

public class Solution {
    /**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public static int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int start = 0, end = A.length -1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (A[start] > target) {
            return start;
        } else {
            return end;
        }
    }

    public static void main(String[] args) {
        Solution.searchInsert(new int[]{1,3,5,6}, 7);
    }
}