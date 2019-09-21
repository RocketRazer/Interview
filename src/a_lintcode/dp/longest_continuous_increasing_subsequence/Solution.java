package a_lintcode.dp.longest_continuous_increasing_subsequence;

public class Solution {
    /**
     * @param A: An array of Integer
     * @return: an integer
     */
//    public int longestIncreasingContinuousSubsequence(int[] A) {
//        int longest = 1;
//
//        int count = 1;
//        for (int i = 1; i < A.length; i++) {
//            if  (A[i] > A[i-1]) {
//                count++;
//            } else {
//                count = 1;
//            }
//            longest = Math.max(longest, count);
//        }
//
//        count = 1;
//        for (int i = A.length - 2; i >= 0; i--) {
//            if  (A[i] > A[i+1]) {
//                count++;
//            } else {
//                count = 1;
//            }
//            longest = Math.max(longest, count);
//        }
//
//        return longest;
//    }



        /**
         * @param nums: An integer array
         * @return: The length of LIS (longest increasing subsequence)
         */
        public int longestIncreasingContinuousSubsequence(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int longest = caculateLongest(nums);

            int i = 0, j = nums.length -1;
            while (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            }

            longest = Math.max(longest, caculateLongest(nums));

            return longest;
        }

        private int caculateLongest(int[] nums) {
            int[] f = new int[nums.length + 1];
            f[0] = 0;
            f[1] = 1;
            int longest = 1;
            for (int i = 2; i <= nums.length; i++) {
                f[i] = 1;
                if (nums[i-1] > nums[i-2]) {
                    f[i] += f[i-1];
                }

                longest = Math.max(longest, f[i]);
            }

            return longest;
        }



    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{5,4,2,1};
        System.out.println(s.longestIncreasingContinuousSubsequence(nums));
    }
}