package leetcode.array_of_double_pairs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static boolean canReorderDoubled(int[] A) {
        if (A == null || A.length == 0) return false;

        int n = A.length;

        if (n % 2 != 0) return false;

        // value -> count
        Map<Integer, Integer> map = new HashMap<>();

        for (Integer i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            int curValue = A[i];
            int doubleValue = curValue * 2;

            if (map.containsKey(curValue) && map.containsKey(doubleValue) && map.get(doubleValue) > 0) {
                map.put(curValue, map.get(curValue) - 1);
                map.put(doubleValue, map.get(doubleValue) - 1);

                if (map.get(doubleValue) == 0) {
                    map.remove(doubleValue);
                }
            }

            if (map.containsKey(curValue) && map.get(curValue) == 0) {
                map.remove(curValue);
            }


        }

        return map.size() == 0;
    }


    public static void main(String[] args) {
        int[] a = {1,2,1,-8,8,-4,4,-4,2,-2};

        System.out.println(canReorderDoubled(a));
    }
}