package a_lintcode.basic.wk2.maximum_average_subarray;

public class Solution {
    /**
     * @param nums: an array
     * @param k: an integer
     * @return: the maximum average value
     */
    public static double findMaxAverage(int[] nums, int k) {
        int maxKSum = 0;

        int i;
        for (i = 0; i < k; i++) {
            maxKSum += nums[i];
        }

        i = 0;
        int j = k;
        int preSum = maxKSum;
        while (i < nums.length - k) {
            preSum = preSum - nums[i] + nums[j];
            maxKSum = Math.max(maxKSum, preSum);
            i++;
            j++;
        }

        return maxKSum / (double)k ;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4,2,1,3,3};
        Solution.findMaxAverage(a, 2);
    }
}