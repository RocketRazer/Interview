package a_google;

import java.util.Map;
import java.util.TreeMap;

/**
 * We need to jump higher and lower alternately to the end.

 Take [5,1,3,4,2] as example.

 If we start at 2,
 we can jump either higher first or lower first to the end,
 because we are already at the end.
 higher(2) = true
 lower(2) = true

 If we start at 4,
 we can't jump higher, higher(4) = false
 we can jump lower to 2, lower(4) = higher(2) = true

 If we start at 3,
 we can jump higher to 4, higher(3) = lower(4) = true
 we can jump lower to 2, lower(3) = higher(2) = true

 If we start at 1,
 we can jump higher to 2, higher(1) = lower(2) = true
 we can't jump lower, lower(1) = false

 If we start at 5,
 we can't jump higher, higher(5) = false
 we can jump lower to 4, lower(5) = higher(4) = false


 */
public class OldEvenJump {
    public static int oddEvenJumps(int[] A) {
        int n  = A.length, res = 1;
        boolean[] higher = new boolean[n], lower = new boolean[n];

        higher[n - 1] = lower[n - 1] = true;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        // <Value, Index>
        map.put(A[n - 1], n - 1);

        for (int i = n - 2; i >= 0; i--) {
            Map.Entry hi = map.ceilingEntry(A[i]), lo = map.floorEntry(A[i]);
            if (hi != null) higher[i] = lower[(int)hi.getValue()];
            if (lo != null) lower[i] = higher[(int)lo.getValue()];
            if (higher[i]) res++;
            map.put(A[i], i);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {5,1,3,4,2};

        System.out.println(oddEvenJumps(nums));
    }
}
