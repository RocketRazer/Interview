package a_google;


import java.util.Comparator;
import java.util.PriorityQueue;

public class CampusBike {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                if (a.dist != b.dist) {
                    return a.dist - b.dist;
                } else {
                    if (a.worker_idx != b.worker_idx) {
                        return a.worker_idx - b.worker_idx;
                    } else {
                        return a.bike_idx - b.bike_idx;
                    }
                }
            }
        });

        int m = workers.length;
        int n = bikes.length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int dist = calculateDist(workers[i], bikes[j]);
                pq.offer(new Pair(i, j, dist));
            }
        }

        int[] res = new int[n];

        boolean[] visited_worker = new boolean[m];
        boolean[] visited_bike = new boolean[n];

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (!visited_worker[cur.worker_idx] && !visited_bike[cur.bike_idx]) {
                res[cur.worker_idx] = cur.bike_idx;
                visited_worker[cur.worker_idx] = true;
                visited_bike[cur.bike_idx] = true;
            }
        }

        return res;
    }

    private int calculateDist (int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}

class Pair {
    int dist;
    int worker_idx;
    int bike_idx;

    public Pair(int worker_idx, int bike_idx, int dist) {
        this.worker_idx = worker_idx;
        this.bike_idx = bike_idx;
        this.dist = dist;
    }
}