package airbnb.A;

import airbnb.onsite.CheapeastFlightsWithKStops;

import java.util.*;

/**

 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 0
 Output: 500

 Time Complexity: O( E + nlogn), where E is the total number of flights.

 Space Complexity: O(n), the size of the heap.

 */
public class ACheapeastFlightsWithKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> srcToDestWithPrice = new HashMap<>();

        for (int[] f : flights) {
            Map<Integer, Integer> destWithPrice = srcToDestWithPrice.getOrDefault(f[0], new HashMap<>());
            destWithPrice.put(f[1], f[2]);
            srcToDestWithPrice.put(f[0], destWithPrice);
        }


        PriorityQueue<CityNode> pq = new PriorityQueue<>((a,b) -> a.priceToHere - b.priceToHere);
        pq.offer(new CityNode(src, 0, K+1, Arrays.asList(src)));

        while (!pq.isEmpty()) {
            CityNode cur = pq.poll();
            int curCity = cur.city;
            int curPrice = cur.priceToHere;
            int remainingFLights = cur.remainingFlights;
            List<Integer> curPath = cur.path;

            if (curCity == dst) {
                for (int i = 0; i < curPath.size(); i++) {
                    System.out.printf(curPath.get(i) + "");
                    if (i != curPath.size() -1) {
                        System.out.printf("->");
                    }
                }
                return curPrice;
            }

            if (remainingFLights > 0) {
                Map<Integer, Integer> nextCityWithPrice = srcToDestWithPrice.get(curCity);
                for (int nextCity : nextCityWithPrice.keySet()) {
                    int nextPrice = curPrice + nextCityWithPrice.get(nextCity);
                    List<Integer> nextPath = new ArrayList<>(curPath);
                    nextPath.add(nextCity);
                    pq.offer(new CityNode(nextCity, nextPrice, remainingFLights - 1, nextPath));
                }
            }
        }

        return -1;
    }

    class CityNode {
        int city;
        int priceToHere;
        int remainingFlights;
        List<Integer> path;

        public CityNode(int city, int priceToHere, int remainingFlights, List<Integer> path) {
            this.city = city;
            this.priceToHere = priceToHere;
            this.remainingFlights = remainingFlights;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        CheapeastFlightsWithKStops s = new CheapeastFlightsWithKStops();
        int[][] flights = {{0,1,100},{1,2,100},{0,2,300}, {0, 3, 50}, {3, 2, 100}};
        System.out.println(s.findCheapestPrice(3, flights, 0, 2, 0));

    }


}


