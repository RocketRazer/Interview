package a_lintcode.basic.wk7.pos_neg_interleaving;

public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length <= 2) {
            return;
        }

        int left = 0, right = A.length - 1;
        while (left <= right) {
            while (left <= right && A[left] < 0) {
                left++;
            }

            while (left <= right && A[right] >=0 ) {
                right--;
            }

            if (left <= right) {
                swap(A, left, right);
                left++;
                right--;
            }
        }

        //left is the first index that number >= 0
        //number of < 0 is left
        if (2 * left < A.length) {
            interleave(A);
        } else {
            reverse(A);
            interleave(A);
        }
    }

    // the number of first category >= sencond category
    private void interleave(int[] A) {
        int left = 1, right = A.length - 1;
        while (left <= right) {
            if (validToSwap(A, left)) {
                swap(A, left + 1, right);
            }

            left++;
            right--;
        }
    }

    private boolean validToSwap(int[] A, int i) {
        return (A[i] > 0 && A[i+1] > 0) || (A[i] < 0 && A[i+1] < 0);

    }

    private void reverse(int[] A) {
        int left = 0, right = A.length - 1;
        while (left <= right) {
            swap(A, left++, right--);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-1, -2, 1};
        s.rerange(nums);
    }
}
