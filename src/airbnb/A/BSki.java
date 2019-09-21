package airbnb.A;

import java.util.*;

public class BSki {

    static class DFS {


        public static void main(String[] args) {
            DFS sol = new DFS();
            List<List<String>> paths = new ArrayList<>();
            paths.add(Arrays.asList("A", "B", "2"));
            paths.add(Arrays.asList("A", "C", "3"));
            paths.add(Arrays.asList("B", "D", "5"));
            paths.add(Arrays.asList("B", "E", "6"));
            paths.add(Arrays.asList("C", "E", "4"));
            paths.add(Arrays.asList("C", "F", "4"));
            paths.add(Arrays.asList("D", "H", "7"));
            paths.add(Arrays.asList("E", "H", "6"));
            paths.add(Arrays.asList("H", "I", "1"));
            paths.add(Arrays.asList("H", "J", "2"));
            paths.add(Arrays.asList("F", "J", "3"));
            List<List<String>> points = new ArrayList<>();
            points.add(Arrays.asList("A", "5"));
            points.add(Arrays.asList("B", "7"));
            points.add(Arrays.asList("C", "6"));
            points.add(Arrays.asList("D", "2"));
            points.add(Arrays.asList("E", "4"));
            points.add(Arrays.asList("F", "7"));
            points.add(Arrays.asList("H", "7"));
            points.add(Arrays.asList("I", "3"));
            points.add(Arrays.asList("J", "2"));
            List<String> ends = Arrays.asList("I", "J");
            System.out.println(sol.findMaxScore(paths, points, "A"));
            System.out.println(sol.maxPath);

        }

        public List<String> maxPath = new ArrayList<>();
        public int maxScore = -1;
        Map<String, Map<String, Integer>> srcToDestWithDistant;
        Map<String, Integer> pointsBySrc;
        Map<String, Integer> maxPointsBySrc = new HashMap<>();

        private int findMaxScore(List<List<String>> paths, List<List<String>> points, String start) {

           srcToDestWithDistant = new HashMap<>();
            for (List<String> list : paths) {
                Map<String, Integer> destWithDistant = srcToDestWithDistant.getOrDefault(list.get(0), new HashMap<>());
                destWithDistant.put(list.get(1), Integer.parseInt(list.get(2)));
                srcToDestWithDistant.put(list.get(0), destWithDistant);
            }

            pointsBySrc = new HashMap<>();
            for (List<String> list : points) {
                pointsBySrc.putIfAbsent(list.get(0), Integer.parseInt(list.get(1)));
            }

            List<String> curPath = new ArrayList<>();

            dfs(start, curPath, 2 * pointsBySrc.get(start));

            return maxScore;
        }

        private void dfs(String curPoint, List<String> curPath, Integer curScore) {
            curPath.add(curPoint);

            Map<String, Integer> destWithDistant = srcToDestWithDistant.getOrDefault(curPoint, new HashMap<>());

            if (destWithDistant.size() == 0) {
                if (curScore > maxScore) {
                    maxScore = curScore;
                    maxPath = new ArrayList<>(curPath);
                }
                curPath.remove(curPath.size() -1);
                return;
            }


            for (String next : destWithDistant.keySet()) {
                int nextScore = curScore + destWithDistant.get(next) + 2 * pointsBySrc.get(next);

                if (maxPointsBySrc.containsKey(next) && maxPointsBySrc.get(next) > nextScore) {
                    continue;
                } else {
                    maxPointsBySrc.put(next, nextScore);
                }
                dfs(next, curPath, nextScore);
            }
            curPath.remove(curPath.size() -1);
        }
    }
}
