package a_lintcode.basic.wk6.subarray_sum_closet;

import java.util.*;

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        int[] result = new int[2];
        int[] sameValueResult = new int[2];

        if (nums == null || nums.length == 0) {
            return result;
        }

        // prefixSum -> List<prefixSumIndex>
        Map<Integer, List<Integer>> map = new HashMap();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        map.put(0, list);

        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i+1] = prefixSum[i] + nums[i];
            if (map.containsKey(prefixSum[i + 1])) {
                map.get(prefixSum[i+1]).add(i+1);
            }

            List<Integer> tmp = new ArrayList<>();
            tmp.add(i+1);
            map.put(prefixSum[i+1], tmp);
        }

        Arrays.sort(prefixSum);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < prefixSum.length - 1; i++) {
            int diff = prefixSum[i+1] - prefixSum[i];
            if (diff < minDiff) {
                result[0] = Math.min(map.get(prefixSum[i+1]).get(0), map.get(prefixSum[i]).get(0)) - 1;
                result[1] = Math.max(map.get(prefixSum[i+1]).get(0), map.get(prefixSum[i]).get(0));
                minDiff = diff;
            }
        }

        return result;
    }

//    public int[] subarraySumClosest(int[] nums) {
//        int[] res = new int[2];
//        if (nums == null || nums.length == 0) {
//            return res;
//        }
//
//        int len = nums.length;
//        if(len == 1) {
//            res[0] = res[1] = 0;
//            return res;
//        }
//        Pair[] sums = new Pair[len+1];
//        int prev = 0;
//        sums[0] = new Pair(0, 0);
//        for (int i = 1; i <= len; i++) {
//            sums[i] = new Pair(prev + nums[i-1], i);
//            prev = sums[i].sum;
//        }
//        Arrays.sort(sums, new Comparator<Pair>() {
//            public int compare(Pair a, Pair b) {
//                return a.sum - b.sum;
//            }
//        });
//        int ans = Integer.MAX_VALUE;
//        for (int i = 1; i <= len; i++) {
//
//            if (ans > sums[i].sum - sums[i-1].sum) {
//                ans = sums[i].sum - sums[i-1].sum;
//                int[] temp = new int[]{sums[i].index - 1, sums[i - 1].index - 1};
//                Arrays.sort(temp);
//                res[0] = temp[0] + 1;
//                res[1] = temp[1];
//            }
//        }
//
//        return res;
//    }

    public static void main(String[] args) {
//        Solution s = new Solution();
//        s.subarraySumClosest(new int[]{6,-4,-8,3,1,7});

        int[] test = new int[]{6,-4,-8,3,1,7};
        for (Integer i : test) {
            System.out.println(i);
        }
    }

    class Pair {
        int sum;
        int index;
        public Pair(int s, int i) {
            sum = s;
            index = i;
        }
    }

}
