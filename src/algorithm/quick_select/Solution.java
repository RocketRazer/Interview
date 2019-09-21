package algorithm.quick_select;

import java.util.*;

public class Solution {


    public static int quickSelect(int[] A, int start, int end , int k) {
        Random rand = new Random();

        if (start == end)
            return A[start];

        int left = start, right = end;
        int pivot = A[rand.nextInt(end - start + 1)  + start];

        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }

            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }

        if (right >= k && right >= start)
            return quickSelect(A, start, right, k);
        else if (left <= k && left <= end)
            return quickSelect(A, left, end, k);
        else
            return A[k];
    }

    public static void main(String[] args) {
//        int[] a = {9,3,2,4,8};
//
//        System.out.println(quickSelect(a, 0, a.length -1, 5 - 4));
        final String test = "123";


        Map<String, String> map = new HashMap<>();

        map.put(null, "123");

        System.out.println(map);

        Set<String> set = new HashSet<>();
        set.add(null);

        System.out.println(set);

        Integer.parseInt("12");
        Integer.valueOf("12");
    }
}
