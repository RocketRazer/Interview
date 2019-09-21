package a_google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnorderedBinarySearch {

    /**
     * 假设给你一个有序数组，，那么如果二分搜索其中的每个数，必定都能找到。
     如果给你同样的数组，但是却是打乱的，然后使用同样的二分搜索来搜索其中的每个数，会有些数找不到。
     请你用 O(N)，返回哪些数找不到。


     再加一个例子：
     Given nums: [22, 92, 25, 16, 29, 15, 49, 22, 38, 61]
     Can't find: [16, 49, 25, 92, 15]

     对于任意数组中的元素，如果用标准的二分法搜索搜索不到，就是最后答案的一员。当然下面的是测试代码，达不到楼顶要求的O(N)了。

     for (int j : nums)
     if (Arrays.binarySearch(nums, j) < 0)
     System.out.println("Can't find " + j);



     (1) 写一个Binary Search算法。
     (2) 用你刚才写的算法在一个未排序的数组（假设元素没有重复）中查找数字，那么哪些数字是在数组中的但不会被找到？

     简单方法是对于每个数字调用(1)看看能不能找到，时间O(n*log(n))
     优化是可以有O(n)的方法，根据(1)的写法进行递归并maintain一个range

     这题是不是可以直接对数组模拟一次没有target的二分搜索，但是不同于平时二分搜索的是，两边都搜，但是由之前执行过的搜索决定现在正在查看的数是不是valid。最后每个数只会被access一遍，所以是On。


     */



    private static int[] findInvalid(int[] nums) {
        List<Integer> invalid = new ArrayList<>();
        binaryFindInvalid(0, nums.length-1, nums, invalid, Integer.MIN_VALUE, Integer.MAX_VALUE);

        return invalid.stream().mapToInt(i ->i).toArray();
    }

    private static void binaryFindInvalid(int left, int right, int[] nums, List<Integer> invalid, int minValue, int maxValue) {
        if (left > right) {
            return;
        }
        int midIdx = (right - left) / 2 + left;
        int midVal = nums[midIdx];

        if (midVal < minValue || midVal > maxValue) {
            invalid.add(midVal);
        }

        binaryFindInvalid(left, midIdx - 1, nums, invalid, minValue, midVal);
        binaryFindInvalid(midIdx + 1, right, nums, invalid, midVal, maxValue);

    }

    private static void printExpectedResult(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i : nums) {
            if (Arrays.binarySearch(nums, i) < 0)
                res.add(i);
        }
        Collections.sort(res);
        System.out.print("Expected: ");
        System.out.println(res);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{22, 92, 25, 16, 29, 15, 49, 22, 38, 61};
        printExpectedResult(nums);

        int[] res = findInvalid(nums);
        Arrays.sort(res);
        System.out.print("Actual ");
        System.out.println(Arrays.toString(res));
    }
}
