package airbnb.onsite;

/**
 * Given a directed graph, find the minimal number of vertices that can traverse all the vertices from them.
 * For example
 * 2->3->1->2->0, 4->5
 * Then we need [1, 4] (or [2, 4], [3, 4]) to traverse all the vertices.
 * Only one solution is needed if there are more than one possibilities.
 */

import java.util.*;

public class MinVerticesTraverseGraph {
    public List<Integer> getMin(int[][] edges, int n) {
        Set<Integer> sourceMap = new HashSet<>();

        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new HashSet<>());
        }
        // 建立邻接表
        for (int[] edge : edges) {
            nodes.get(edge[0]).add(edge[1]);
        }

        // 用来记录访问过的点
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            //如果已经访问过了就跳过， visited 只加不减
            if (visited.contains(i)) {
                continue;
            }

            //sourceMap 记录所有当作source访问过的点
            //当有以其他点作为source访问过source里的点的时候，就把这个点从source里删掉
            sourceMap.add(i);
            visited.add(i);

            //记录此次以i为source dfs访问过的所有的点，递归出口
            Set<Integer> thisTimeVisited = new HashSet<>();
            dfs(sourceMap, nodes, i, i, visited, thisTimeVisited);
        }

        return new ArrayList<>(sourceMap);
    }

    private void dfs(Set<Integer> sourceMap, Map<Integer, Set<Integer>> nodes,
                     int cur,
                     int start,  // source start的点
                     Set<Integer> visited, Set<Integer> thisTimeVisited) {
        for (int next : nodes.get(cur)) {
            //当有以其他点作为source访问过source里的点的时候，就把这个点从source里删掉
            if (sourceMap.contains(next) && next != start) {
                sourceMap.remove(next);
            }
            //如果这次还没访问，继续以这个点往下dfs
            //这也是递归的出口， 如果所有点以start 为source都访问过了，递归就退出了
            if (!thisTimeVisited.contains(next)) {
                thisTimeVisited.add(next);
                visited.add(next);
                dfs(sourceMap, nodes, next, start, visited, thisTimeVisited);
            }
        }
    }

    public static void main(String[] args) {
        MinVerticesTraverseGraph mvtg = new MinVerticesTraverseGraph();
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

