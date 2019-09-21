package a_lintcode.improve.iw7.subarray_sum_ii;

public class Solution {
    /**
     * @param nums: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] nums, int start, int end) {
        int n = nums == null ? 0 : nums.length;
        if (n == 0 || start > end || end <= 0) {
            return 0;
        }

        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int count = 0;
        for (int i = 1, l = 0, r = 0; i <= n; i++) {
            while (l < i && prefixSum[i] - prefixSum[l] > end) {
                l++;
            }
            while (r < i && prefixSum[i] - prefixSum[r] >= start) {
                r++;
            }
            count += r - l;
        }
        return count;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        Solution s = new Solution();
        s.subarraySumII(nums, 1, 3);
    }
}
