package amazon.oa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Amazon Fresh delivery
 *
 * https://1o24bbs.com/t/topic/3152
 */
public class FreshDelivery {

    public static int[][] findKClosest(int numOfDestinations, int[][] allLocations, int numDeliveries) {
        if (allLocations == null || allLocations.length == 0) return new int[0][0];

        if (numDeliveries >= numOfDestinations) return allLocations;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return getDistance(o1) - getDistance(o2);
            }
        });

        for (int[] location : allLocations) {
            pq.offer(location);
        }

        int[][] res = new int[numDeliveries][2];

        int i = 0;
        while (i < numDeliveries) {
            res[i] = pq.poll();
            i++;
        }

        return res;
    }

    private static int getDistance(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }

    public static void main(String[] args) {
        int numOfDestinations = 3, numDeliveries = 2;
        int[][] allLocations = {{1,2}, {3,4}, {1, -1}};

        int[][] res = findKClosest(numOfDestinations, allLocations, numDeliveries);
        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
    }
}
