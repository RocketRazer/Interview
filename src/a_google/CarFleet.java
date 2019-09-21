package a_google;

import java.util.TreeMap;

public class CarFleet {
    public static int carFleet(int target, int[] position, int[] speed) {
        // <distance2Target, arriveTime>
        TreeMap<Integer, Double> map = new TreeMap<>();

        for (int i = 0; i < position.length; i++) {
            int remainDist = target - position[i];
            map.put(remainDist, remainDist * 1.0 / speed[i]);
        }

        double curArriveTime = 0;
        int res = 0;

        for (Double arriveTime : map.values()) {
            if (arriveTime > curArriveTime) {
                res++;
                curArriveTime = arriveTime;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        //target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
        carFleet(12, new int[]{10,8,0,5,3}, new int[]{2,4,1,1,3});
    }

}
