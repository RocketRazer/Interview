package a_lintcode.improve.iw4.sliding_window_maximum;

import java.util.*;

// deque中存储位置
public class Solution {
//    int[] a;
//
//    void inQueue(Deque<Integer> deque, int pos) {
//        while (!deque.isEmpty() && a[deque.peekLast()] >= a[pos]) {
//            deque.pollLast();
//        }
//        deque.offer(pos);
//    }
//
//    void outQueue(Deque<Integer> deque, int pos) {
//        if (deque.peekFirst() == pos) {
//            deque.pollFirst();
//        }
//    }
//
//    /**
//     * @param nums: A list of integers.
//     * @return: The maximum number inside the window at each moving.
//     */
//    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
//        a = nums;
//        // write your code here
//        ArrayList<Integer> ans = new ArrayList<Integer>();
//        Deque<Integer> deque = new ArrayDeque<Integer>();
//        if (nums.length == 0) {
//            return ans;
//        }
//        for (int i = 0; i < k - 1; i++) {
//            inQueue(deque, i);
//        }
//
//        for(int i = k - 1; i < nums.length; i++) {
//            inQueue(deque, i);
//            ans.add(a[deque.peekFirst()]);
//            outQueue(deque, i - k + 1);
//        }
//        return ans;
//
//    }

    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();

        if (nums == null || nums.length == 0 || k <= 0) {
            return res;
        }

        // deque stores the indexs of element
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k-1; i++) {
            inQueue(deque, i, nums);
        }

        for (int i = k-1; i < nums.length; i++) {
            inQueue(deque, i, nums);
            res.add(nums[deque.peekFirst()]);
            outQueue(deque, i - k + 1);
        }

        return res;
    }

    private void inQueue(Deque<Integer> deque, int index, int[] nums) {
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[index]) {
            deque.pollLast();
        }
        deque.offer(index);
    }

    private void outQueue(Deque<Integer> deque, int index) {
        if (index == deque.peekFirst()) {
            deque.pollFirst();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 2, 7, 7, 8};
        List<Integer> res = s.maxSlidingWindow(nums, 3);
        System.out.println(res.toString());

    }
}