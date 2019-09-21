package airbnb.A;

import java.util.Arrays;

public class AWiggleSortII {
    /**
     * Time: O(nlogn)
     * Space O(n)
     * @param nums
     */
    public void wiggleSort1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int[] tmp = Arrays.copyOf(nums, nums.length);

        Arrays.sort(tmp);

        int medianIndex = (tmp.length + 1) / 2 - 1;
        int x = medianIndex, y = tmp.length - 1;

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = tmp[x--];
            } else {
                nums[i] = tmp[y--];
            }
        }
    }


    /**
     * Time: O(n) Space O(n)

     (1) elements smaller than the 'median' are put into the last even slots
     (2) elements larger than the 'median' are put into the first odd slots
     (3) the medians are put into the remaining slots.
     * @param nums
     */
    public void wiggleSort2(int[] nums) {
        final int n = nums.length;

        int mid = partition(nums, 0, n-1, n/2);
        int[] ans = new int[n];

        // set ans all values to media
        for (int i = 0; i < n; i++) {
            ans[i] = mid;
        }

        int largerIdx = 1, smallerIdx = (n%2 == 0) ? n-2 : n-1;

        /*
        (1) elements smaller than the 'median' are put into the last even slots

        (2) elements larger than the 'median' are put into the first odd slots

        (3) the medians are put into the remaining slots.
        */
        for (int i = 0; i < n; i++) {
            if (nums[i] < mid) {
                ans[smallerIdx] = nums[i];
                smallerIdx -= 2;
            } else if (nums[i] > mid) {
                ans[largerIdx] = nums[i];
                largerIdx += 2;
            }
        }

        for (int i = 0; i < n; i++) {
            nums[i] = ans[i];
        }
    }

    public static int partition(int[] nums, int start, int end, int rank) {
        int left = start, right = end;
        int now = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= now) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= now) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = now;
        if (left - start == rank) {
            return now;
        } else if (left - start < rank) {
            return partition(nums, left + 1, end, rank - (left - start + 1));
        } else {
            return partition(nums, start, right - 1, rank);
        }
    }


    public int partition2(int[] nums, int start, int end, int rank) {
        int left = start, right = end;
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] > pivot) {
                right--;
            }

            nums[left] = nums[right];

            while(left < right && nums[left] < pivot) {
                left++;
            }
            nums[right] = nums[left];
        }

        nums[left] = pivot;

        if (left - start == rank) {
            return pivot;
        } else if (left - start < rank) {
            // select pivot is small
            return partition(nums, left + 1, end, rank - (left - start + 1));
        } else {
            // select pivot is bigger
            return partition(nums, start, right - 1, rank);
        }
    }
}
