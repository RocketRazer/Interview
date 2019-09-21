package airbnb.onsite;

import java.util.*;

public class CheapeastFlightsWithKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (flights == null || flights.length == 0) {
            return 0;
        }


        Map<Integer, Map<Integer, Integer>> srcToDestWithPrice = new HashMap<>();

        for (int[] f : flights) {
            Map<Integer, Integer> destWithPrice = srcToDestWithPrice.getOrDefault(f[0], new HashMap<>());
            destWithPrice.put(f[1], f[2]);
            srcToDestWithPrice.put(f[0], destWithPrice);
        }

        // min heap
        PriorityQueue<CityNode> pq = new PriorityQueue<>((a, b) -> a.priceToHere - b.priceToHere);
        pq.offer(new CityNode(src, 0, K+1, Arrays.asList(src)));

        while (!pq.isEmpty()) {
            CityNode cur = pq.poll();
            int curCity = cur.city;
            int curStops = cur.remainFlights;
            int curPriceToHere = cur.priceToHere;
            List<Integer> curPath = cur.path;

            if (curCity == dst) {
                for (int i = 0 ; i < curPath.size(); i++) {
                    System.out.print(i);
                    if (i != curPath.size() -1) {
                        System.out.print("->");
                    }
                }
                System.out.println();
                return curPriceToHere;
            }

            if (curStops > 0) {
                Map<Integer, Integer> destWithPrice = srcToDestWithPrice.getOrDefault(curCity, new HashMap<>());
                for (int nextCity : destWithPrice.keySet()) {
                    int nextPrice = curPriceToHere + destWithPrice.get(nextCity);
                    List<Integer> newPath = new ArrayList<>(curPath);
                    newPath.add(nextCity);
                    pq.offer(new CityNode(nextCity, nextPrice, curStops - 1, newPath));
                }
            }

        }
        return -1;

    }


    class CityNode {
        int city;
        int priceToHere;
        int remainFlights;
        List<Integer> path;

        public CityNode(int city, int priceToHere, int remainFlights, List<Integer> path) {
            this.city = city;
            this.priceToHere = priceToHere;
            this.remainFlights = remainFlights;
            this.path = new ArrayList<>(path);
        }
    }


    public static void main(String[] args) {
        CheapeastFlightsWithKStops s = new CheapeastFlightsWithKStops();
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}, {0, 3, 50}, {3, 2, 200}};
        System.out.println(s.findCheapestPrice(3, flights, 0, 2, 1));

    }
}
