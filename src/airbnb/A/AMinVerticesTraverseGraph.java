package airbnb.A;

/**
 * Given a directed graph, find the minimal number of vertices that can traverse all the vertices from them.
 * For example
 * 2->3->1->2->0, 4->5
 * Then we need [1, 4] (or [2, 4], [3, 4]) to traverse all the vertices.
 * Only one solution is needed if there are more than one possibilities.
 */

import java.util.*;

public class AMinVerticesTraverseGraph {
    public List<Integer> getMin(int[][] edges, int n) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
        }


        Set<Integer> source = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) {
                continue;
            }

            source.add(i);
            visited.add(i);

            Set<Integer> thisTimeVisited = new HashSet<>();

            dfs(i, i, thisTimeVisited, visited, source, graph);
        }

        return new ArrayList<Integer>(source);
    }

    private void dfs(int startPoint, int curPoint,
                     Set<Integer> thisTimeVisited,
                     Set<Integer> visited,
                     Set<Integer> source,
                     Map<Integer, Set<Integer>> graph) {

        for (Integer neighbor : graph.get(curPoint)) {
            if (source.contains(neighbor) && neighbor != startPoint) {
                source.remove(neighbor);
            }

            if (!thisTimeVisited.contains(neighbor)) {
                thisTimeVisited.add(neighbor);
                visited.add(neighbor);

                dfs(startPoint, neighbor, thisTimeVisited, visited, source, graph);
            }
        }

    }


    public static void main(String[] args) {
        AMinVerticesTraverseGraph mvtg = new AMinVerticesTraverseGraph();
//    1->2->3->1, 2->0->0
//        int[][] edges1 = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
//        System.out.println(mvtg.getMin(edges1, 4));
//      0  1  2  3  4  5  6  7  8  9
//    0[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
//    1[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
//    2[0, 0, 0 ,0, 0, 0, 0, 0, 0, 1]
//    3[0, 0, 0, 1, 0, 1, 0, 1, 0, 0]
//    4[0, 0, 0, 0, 0, 0 ,0, 0, 1, 0]
//    5[0, 0, 0, 0, 0, 0, 0, 0, 1, 0]
//    6[0, 0, 0, 0, 0, 0, 1, 0, 0 ,0]
//    7[0, 0, 0, 0, 1, 0, 0, 0, 0, 0]
//    8[0, 0, 0, 0, 0, 0, 0, 1, 0, 0]
//    9[0, 0, 0, 1, 0, 0, 1, 0, 0, 0]
        int[][] edges2 = {{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}};
        System.out.println(mvtg.getMin(edges2, 10));
//    0->1->0, 2->3->2->1
        int[][] edges3 = {{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
        System.out.println(mvtg.getMin(edges3, 4));
    }
}

