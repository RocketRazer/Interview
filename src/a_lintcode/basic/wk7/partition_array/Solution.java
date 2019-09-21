package a_lintcode.basic.wk7.partition_array;

public class Solution {
    /**
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray1(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {

            while (left <= right && nums[left] < k) {
                left++;
            }

            while (left <= right && nums[right] >= k) {
                right--;
            }

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right--;
            }
        }
        // 循环出来的时候 left一定指向的是第一个 >= k
        return left;
    }

    public void partitionArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int left =0, right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] % 2 == 0) {
                left++;
            }

            while (left <= right && nums[right] % 2 == 1) {
                right--;
            }

            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;

                left++;
                right--;
            }
        }

        System.out.println(left);
        return;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{5, 4, 3, 2, 1};
        s.partitionArray(nums);
    }
}