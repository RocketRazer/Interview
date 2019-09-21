package a_lintcode.basic.wk5.next_permutation;

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers
     */
    public int[] nextPermutation(int[] nums) {
        // write your code here
        int i = nums.length - 2;
        while (i >=0 && nums[i] >= nums[i+1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(i, j, nums);
        }

        reverse(i+1, nums.length -1, nums);

        return nums;
    }


    private void reverse (int start, int end, int[] nums) {
        while (start < end) {
            swap(start, end, nums);
            start++;
            end--;
        }
    }
    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,3,2,3,3,1};
        s.nextPermutation(nums);
        for (Integer i : nums) {
            System.out.print(i + ", ");
        }
    }
}