package a_lintcode.basic.wk2.find_k_closest_elements;

public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        int[] res = new int[k];

        if ( A == null || A.length == 0 || k == 0) {
            return res;
        }

        int fistLessThanTargetIndex = findFirstTarget(A, target);


        int left = fistLessThanTargetIndex;
        int right;
        if (fistLessThanTargetIndex < A.length) {
            right = fistLessThanTargetIndex + 1;
        } else {
            int i = 0;
            while (i < k) {
                res[i++] = A[left--];
            }
            return res;
        }

        int i = 0;

        while (left >= 0 && right < A.length && i < k) {
            if (target - A[left] <= A[right] - target) {
                res[i] = A[left];
                i++;
                left--;
            } else {
                res[i] = A[right];
                i++;
                right++;
            }
        }

        while (i < k) {
            if (left > 0) {
                res[i] = A[left];
                i++;
                left--;
            }

            if (right < A.length) {
                res[i] = A[right];
                i++;
                right++;
            }
        }

        return res;
    }

    private int findFirstTarget(int[] A, int target) {
        int start = 0, end = A.length -1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (target > A[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[end] < target) {
            return end;
        }
        if (A[start] < target) {
            return start;
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.kClosestNumbers(new int[]{1, 4, 6, 8}, 3, 3);
    }
}
