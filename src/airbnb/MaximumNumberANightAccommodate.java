package airbnb;

/**
 * Leetcode 相似问题: Leetcode 198/213/337 House Robber I/II/III
 *
 Provide a set of positive integers (an array of integers). Each integer represents number of nights user request on Airbnb.com.
 If you are a host, you need to design and implement an algorithm to find out the maximum number a night you can accommodate.
 The constrain is that you have to reserve at least one day between each request, so that you have time to clean the room.
 Given a set of numbers in an array which represent number of consecutive days of AirBnB reservation requested,
 as a host, pick the sequence which maximizes the number of days of occupancy, at the same time, leaving at least 1 day gap
 in between bookings for cleaning. Problem reduces to finding max-sum of non-consecutive array elements.
 */
public class MaximumNumberANightAccommodate {

    public static int maxNights (int[] reservations) {
        if (reservations == null || reservations.length == 0)
            return 0;

        // f[i] represents the max nights can accommodate for the first i reservations
        int[] f = new int[reservations.length + 1];

        f[0] = 0;
        f[1] = reservations[0];
        for (int i = 2; i <= reservations.length; i++) {
            f[i] = Math.max(f[i-2] + reservations[i-1], f[i-1]);
        }

        return f[reservations.length];
    }

    public static int maxNights_rollingArray(int[] reservations) {
        if (reservations == null || reservations.length == 0)
            return 0;


        int f0 = 0;
        int f1 = reservations[0];
        int f2 = 0;
        for (int i = 2; i <= reservations.length; i++) {
            f2 = Math.max(f0 + reservations[i-1], f1);
            f0 = f1;
            f1 = f2;
        }

        return f2;
    }


    public static void main(String[] args) {

        //8
        int[] test1 = {5, 6, 3, 1};
        System.out.println(maxNights_rollingArray(test1) + " expect 8");

        //16
        int[] test2 = {6, 5, 0, 1, 0, 9};
        System.out.println(maxNights_rollingArray(test2) + " expect 16");

        //10
        int[] test3 = {5, 1, 1, 5};
        System.out.println(maxNights_rollingArray(test3) + " expect 10");

        //7
        int[] test4 = {3, 6, 4};
        System.out.println(maxNights_rollingArray(test4) + " expect 7");

        //15
        int[] test5 = {4, 10, 3, 1, 5};
        System.out.println(maxNights_rollingArray(test5) + " expect 15");
    }
}
