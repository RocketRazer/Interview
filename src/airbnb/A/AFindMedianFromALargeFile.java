package airbnb.A;


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
public class AFindMedianFromALargeFile {
    public double findMedian(int[] nums) {
        int total = 0;
        for (int i : nums) {
            total++;
        }

        if (total % 2 == 0) {
            return (findKthNums(nums, total / 2) + findKthNums(nums, total / 2 + 1)) / 2.0;
        } else {
            return findKthNums(nums, total / 2);
        }
    }

    private double findKthNums(int[] nums, int k) {
        long left = Integer.MIN_VALUE;
        long right = Integer.MAX_VALUE;


        while (left <= right) {
            long medianGuess = left + (right - left) / 2l;

            long largestLessThanMiddle = left;
            int count = 0;
            for (int i : nums) {
                if (i <= medianGuess) {
                    count++;
                    largestLessThanMiddle = Math.max(largestLessThanMiddle, i);
                }
            }

            if (count == k) {
                return largestLessThanMiddle;
            } else if (count < k) {
                // middle guess is smaller
                left = medianGuess + 1;
            } else {
                // middle guess is bigger
                right = medianGuess - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        AFindMedianFromALargeFile fm = new AFindMedianFromALargeFile();
//        System.out.println(fm.findMedian(new int[] {-6, 18, 9}));
//        System.out.println(fm.findMedian(new int[] {-20, 0, 7, 5}));
        System.out.println(fm.findMedian(new int[] {1, 2, 2, 2, 3}));


    }
}
