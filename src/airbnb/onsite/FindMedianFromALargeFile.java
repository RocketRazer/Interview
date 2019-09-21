package airbnb.onsite;

import java.util.*;

/**
 * Find the median from a large file of integers. You can not access the numbers by index, can
 * only access it sequentially. And the numbers cannot fit in memory.
 *
 * Solution
 * Integers range from Integer.MIN_VALUE to Integer.MAX_VALUE. We can use rang-based binary search
 * upper bound: large
 * lower bound: small
 * find kth smallest element, k == len / 2 + 1 for median (odd)
 * guess: (small + large) / 2
 * count how many numbers less or equal to guess
 *    if count == k : return largest (res) that is less or equal to guess
 *    if count < k: search for res + 1 to large
 *    if count > k: search for small to res
 *
 * Maximum scanning time is 32
 */
public class FindMedianFromALargeFile {
    public double findMedian(int[] nums) {
        int len = 0;
        for (int num : nums) {
            len++;
        }
        // 1, 2, 3, 4, 5 => k = 3
        // 1, 2, 3, 4, 5, 6 => k = 3, k = 4
        if (len % 2 == 1) {
            return findKthSmallest(nums, len / 2 + 1);
        } else {
            return (findKthSmallest(nums, len / 2) + findKthSmallest(nums, len / 2 + 1)) / 2.0;
        }
    }

    private double findKthSmallest(int[] nums, int k) {
        long left = Integer.MIN_VALUE;
        long right = Integer.MAX_VALUE;
        while (left <= right) {
            int count = 0;
            long res = left;
            long guess = left + (right - left) / 2;
            for (int num : nums) {
                if (num <= guess) {  // must use <= because guess might be the correct answer
                    count++;
                    res = Math.max(res, num);
                }
            }
            if (count == k) {
                return res;
            } else if (count < k) {
                left = guess + 1;
            } else {
                right = guess - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        FindMedianFromALargeFile sol = new FindMedianFromALargeFile();
        int[] nums = new int[100];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        System.out.println(sol.findMedian(nums));
        Arrays.sort(nums);
        System.out.println(nums[49]);
        System.out.println(nums[50]);

        System.out.println("New Test Cases");
        System.out.println(sol.findMedian(new int[] {-6, 18, 9}));
        System.out.println(sol.findMedian(new int[] {-20, 0, 7, 5}));
        System.out.println(sol.findMedian(new int[] {1, 2, 2, 2, 3, 3}));
    }
}

