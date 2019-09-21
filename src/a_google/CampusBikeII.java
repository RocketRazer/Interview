package a_google;


import java.util.HashMap;
import java.util.Map;

public class CampusBikeII {
    Map<String, Integer> memo1 = new HashMap<>();
    public int assignBikes(int[][] workers, int[][] bikes) {
        return dfs(workers, 0, bikes, 0);
    }

    private int dfs(int[][] workers, int index, int[][] bikes, int occupy) {
        if (index >= workers.length) return 0;
        if (memo1.containsKey(index + "," + Integer.toBinaryString(occupy))) {
            return memo1.get(index + "," + Integer.toBinaryString(occupy));
        }
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++) {
            if ((occupy & (1 << i)) == 0) {
                temp = Math.min(temp, getDistance(workers[index], bikes[i]) + dfs(workers, index + 1, bikes, (occupy ^ (1 << i))));
            }
        }
        memo1.put(index + "," + Integer.toBinaryString(occupy), temp);
        return temp;
    }

    private int getDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }


    public static void main(String[] args) {
        CampusBikeII campusBikeII = new CampusBikeII();
        int[][] workers = {{0,0}, {1,1}, {2,0}};
        int[][] bikes = {{1,0}, {2,2}, {2,1}};
        System.out.println(campusBikeII.assignBikes(workers, bikes));
    }

//    private int minTotalDist = Integer.MAX_VALUE;
//
//    public int assignBikes(int[][] workers, int[][] bikes) {
//        int m = workers.length;
//        int n = bikes.length;
//
//        int[][] dist = new int[m][n];
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                dist[i][j] = calculateDist(workers[i], bikes[j]);
//            }
//        }
//
//        boolean[] usedBikes = new boolean[n];
//
//        dfs(0, 0, usedBikes, dist);
//
//        return minTotalDist;
//    }
//
//    private int calculateDist(int[] worker, int[] bike) {
//        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
//    }
//
//
//    private void dfs(int startIdx, int curTotalDist, boolean[] usedBikes, int[][] dist) {
//        if (startIdx == dist.length - 1) {
//            if (curTotalDist < minTotalDist) {
//                minTotalDist = curTotalDist;
//            }
//            return;
//        }
//
//
//        for (int i = 0; i < dist[startIdx].length; i++) {
//            if (usedBikes[i]) {
//                continue;
//            }
//
//            usedBikes[i] = true;
//            dfs(startIdx + 1, curTotalDist + dist[startIdx][i], usedBikes, dist);
//            usedBikes[i] = false;
//        }
//    }
}