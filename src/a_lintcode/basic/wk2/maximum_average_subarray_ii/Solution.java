package a_lintcode.basic.wk2.maximum_average_subarray_ii;

public class Solution {
    /**
     * @param nums: an array of integer
     * @param k: an integer
     * @return: the largest sum
     */
    public int maxSubarray4(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return 0;
        }

        int n = nums.length;

        int max = Integer.MIN_VALUE;
        int sum = 0;
        int preSum = 0;
        int preMin = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (i >= k-1) {
                max = Math.max(max, sum);
            }

            if (i >= k) {
                preSum += nums[i-k];
                preMin = Math.min(preMin, preSum);

                int subArraySum = sum - preMin;
                max = Math.max(max, subArraySum);
            }
        }

        return max;
    }





    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-2,2,-3,4,-1,2,1,-5,3};
        s.maxSubarray4(nums, 5);
    }
}