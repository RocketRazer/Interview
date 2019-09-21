package a_lintcode.improve.iw1.minimum_size_subarray;

public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int j = 0;
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int i =0; i< nums.length; i++) {
            while (j < nums.length && sum < s) {
                sum += nums[j];
                if (sum < s) {
                    j++;
                } else {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
            sum -= nums[i];
        }

        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.minimumSize(new int[]{2,3,1,2,4,3}, 7);
    }
}