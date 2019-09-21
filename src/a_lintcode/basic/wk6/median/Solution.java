package a_lintcode.basic.wk6.median;

import java.util.Random;

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public int median2(int[] nums) {
        // write your code here
        int len = nums.length;
        //题目要求： If there are even numbers in the array, return the N/2-th number after sorted.
        int mid = (len - 1) / 2;
        return partition(nums, 0, len -1, mid);
    }

    private int partition(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }

        int left = start, right = end;
        int pivot = nums[(start + end) / 2];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        if (k <= right) {
            return partition(nums, start, right, k);
        } else {
            return partition(nums, left, end, k);
        }
    }

//    private void swap(int[] nums, int i, int j) {
//        int tmp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = tmp;
//    }

    //version 2
    public int median(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1, mid = (nums.length + 1)/2;
        while (true) {
            int nth = partition(nums, l, r);
            if (nth + 1 == mid) {
                return nums[nth];
            } else if (nth + 1 > mid){
                r = nth - 1;
            } else {
                l = nth + 1;
            }
        }
    }

    public int partition(int[] nums, int start, int end) {
        int randomIndex = new Random().nextInt(end - start + 1) + start;
        System.out.println(randomIndex);
        int pivot = nums[randomIndex], index = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, index++);
            }
        }
        swap(nums, end, index);
        return index;
    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[r];
        nums[r] = nums[l];
        nums[l] = temp;
        return;
    }

    public static void main(String[] args) {
//        Solution s = new Solution();
//        System.out.println(s.median(new int[]{1, 5, 3, 2, 4}));

        int start = 0, end = 4;
        for (int i = 0 ; i < 5; i++) {
            int randomIndex = new Random().nextInt(end - start + 1) + start;
            System.out.println(randomIndex);
        }
    }
}