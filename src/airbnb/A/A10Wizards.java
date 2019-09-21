package airbnb.A;

import airbnb.WizardShortestDistance;

import java.util.*;


/**
 * There are 10 wizards, 0-9.
 * you are given a list that each entry is a list of wizards known by wizard.
 * Define the cost between wizard[i] and wizard[j] as square of different of i and j
 * i.e. Cost(i, j) = (i - j) ^ 2
 * <p>
 * Find the min cost between 0 and 9. Out put the min cost path.
 * <p>
 * Input
 * List<List<Integer>>, 最外层的List的size保证为10， 一次对应编号为0，1,...,9的人认识那些人
 * Output
 * List<Integer> min cost path
 * <p>
 * This is exactly the same problem as MinCostKStops (Flight Tickets) without k stops constrains.
 * <p>
 * We can also use Dijkstra's Algorithm as well
 */


public class A10Wizards {

    public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
        Map<Integer, Map<Integer, Integer>> srcToDestWithCost = new HashMap<>();

        for (int i = 0; i < wizards.size(); i++) {
            Map<Integer, Integer> destWithPrice = srcToDestWithCost.getOrDefault(i, new HashMap<>());
            List<Integer> destList = wizards.get(i);
            for (Integer d : destList) {
                destWithPrice.put(d, (d - i) * (d - i));
            }
            srcToDestWithCost.put(i, destWithPrice);
        }

        PriorityQueue<WizardNode> pq = new PriorityQueue<>((a,b) -> a.costToHere - b.costToHere);
        pq.offer(new WizardNode(source, 0, Arrays.asList(source)));

        while(!pq.isEmpty()) {
            WizardNode cur = pq.poll();

            if (cur.wizard == target) {
                return cur.path;
            }

            Map<Integer, Integer> destWithCost = srcToDestWithCost.get(cur.wizard);
            for (Integer next : destWithCost.keySet()) {
                int nextCost = cur.costToHere + destWithCost.get(next);
                List<Integer> nextPath = new ArrayList<>(cur.path);
                nextPath.add(next);
                pq.offer(new WizardNode(next, nextCost, nextPath));
            }
        }

        // no path
        return new ArrayList<>();
    }

    class WizardNode {
        int wizard;
        int costToHere;
        List<Integer> path;

        public WizardNode(int wizard, int costToHere, List<Integer> path) {
            this.wizard = wizard;
            this.costToHere = costToHere;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        A10Wizards wsd = new A10Wizards();
        int[][] ids = {{1, 5, 9}, {2, 3, 9}, {4}, {}, {}, {9}, {}, {}, {}, {}};
        List<List<Integer>> wizards = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            List<Integer> wizard = new ArrayList<>();
            for (int j = 0; j < ids[i].length; j++) {
                wizard.add(ids[i][j]);
            }
            wizards.add(wizard);
        }

        System.out.println(wsd.getShortestPath(wizards, 0, 9));
    }
}
