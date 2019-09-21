package airbnb.onsite;

import java.util.Arrays;

/**
 *
 https://leetcode.com/problems/wiggle-sort-ii/

 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example 1:

 Input: nums = [1, 5, 1, 1, 6, 4]
 Output: One possible answer is [1, 4, 1, 5, 1, 6].
 Example 2:

 Input: nums = [1, 3, 2, 2, 3, 1]
 Output: One possible answer is [2, 3, 1, 3, 1, 2].
 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?


 */
public class WiggleSort_II {

    /**
     * 方法一：
     先排序， 找到median [(length + 1) / 2]

     然后两个指针，一个指向median -1， 一个指向最后一个



     依次取出一个组成新的array

     Time: O(nlogn)
     space: O(n)
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int[] tmp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tmp);

        //注意： 这里median idx我们取的 （length + 1）/ 2
        //因为是先排小的再排大的， 如果总数是奇数的话，最后一个多出一个小的
        int medianIdx = (tmp.length + 1) / 2;

        //这个要从后往前拿，避免重复的相邻
        //例子： 4556
        int x = medianIdx -1, y = nums.length -1;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = tmp[x--];
            } else {
                nums[i] = tmp[y--];
            }
        }
    }


    /**
     *
      partition find median


     */
    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int[] tmp = Arrays.copyOf(nums, nums.length);


        //注意： 这里median idx我们取的 （length + 1）/ 2
        //因为是先排小的再排大的， 如果总数是奇数的话，最后一个多出一个小的
        int medianIdx = partition(nums, 0, tmp.length - 1, (tmp.length + 1)/2 );

        System.out.println(medianIdx);

        //这个要从后往前拿，避免重复的相邻
        //例子： 4556
        int x = medianIdx -1, y = nums.length -1;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = tmp[x--];
            } else {
                nums[i] = tmp[y--];
            }
        }
    }


    public static void wiggleSort3(int[] nums) {
        final int n = nums.length;
        int[] tem = new int[n];
        for (int i = 0; i < n; i++) {
            tem[i] = nums[i];
        }
        int mid = partition(tem, 0, n-1, n/2);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = mid;
        }

        int l = 1, r = (n%2 == 0) ? n-2 : n-1;
        for (int i = 0; i < n; i++) {
            if (nums[i] < mid) {
                ans[r] = nums[i];
                r -= 2;
            } else if (nums[i] > mid) {
                ans[l] = nums[i];
                l += 2;
            }
        }
        for (int i = 0; i < n; i++) {
            nums[i] = ans[i];
        }
    }

    public static int partition(int[] nums, int l, int r, int rank) {
        int left = l, right = r;
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
        if (left - l == rank) {
            return now;
        } else if (left - l < rank) {
            return partition(nums, left + 1, r, rank - (left - l + 1));
        } else {
            return partition(nums, l, right - 1, rank);
        }
    }

    public static void main(String[] args) {
        WiggleSort_II s = new WiggleSort_II();
        //int[] nums = {3,2,3,3,2,1,1,2,3,1,1,3,2,1,2,2,2,2,1};
        int[] nums = {1,1,2,2,3,3,3};
        //Arrays.sort(nums);
        //System.out.println(Arrays.toString(nums));

        s.wiggleSort3(nums);
        System.out.println(Arrays.toString(nums));
    }


}
