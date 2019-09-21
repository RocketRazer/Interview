package airbnb.onsite;

import java.util.*;

/**
 *
 *
 *
 * https://www.1point3acres.com/bbs/interview/airbnb-software-engineer-464448.html
 输入(String[][] travel, String[][] point)
 输出：int score
 travel的形式是[["start","3","A],["A","4","B"],["B","5","END1"]]
 . 1point3acres
 第一个String为出发点，第二个为从某个点到下一个点的cost，第三个String为结束点，可以想象为滑雪的人从“start"到"A"需要的cost为3
 point的形式为[["A","5"],["B","6"],["END","3"]]
 代表到达每一个点的reward
 所要求的是从start到end的最大score，注意end点可能有很多个，start是固定的
 score的计算方法是reward-cost
 比如说从start到A，cost是3，reward是5，那么A的score就是5-3=2，当然只是举个例子，A的score应该是所有到达A的方式里最大的那个。
 我的做法是先用Hashmap把cost, reward保存下来，然后再用一个Hashmap存每个点的score，做bfs一直到END，可以找到最大的score。
 Follow up 是要求打印从start到end的路径，就是使得score最大的那条路。
 做法是用一个parent把每个点的parent记录下来，再从END一路找parent回去，找到start了就是我们要找的路径。


 https://www.1point3acres.com/bbs/interview/airbnb-engineering-470093.html
 * - 背景是一个滑雪选手从高山上往下滑，会遇到不同的checkpoint，每一个checkpoint有自己的point，然后每个edge有distance。
 * 经过每一个checkpoint所得到的score是通过一个包含point和distance的式子算出来的（比如2 * point +distance之类的）。
 * 最终求从最高点往下滑能得到的最大score是多少，用BFS就可以。
 * - 类似下图，从A出发，最终可以到 I 或者 J，求到I 或者 J 能得到的最大score是多少。
 */
public class Ski {

    class Node {
        String name ;
        int point ;
        Map<String, Integer> nextsWithDistance;
        int score ; // track the max score to get here
        Node(String name, int point) {
            this.name = name ;
            this.point = point ;
            this.nextsWithDistance = new HashMap<>() ;
            this.score = Integer.MIN_VALUE ;
        }
    }

    private int getScore(int point, int dist) {
        return 2 * point + dist ;
    }

    /**
     *
     * @param nodes 每个点对应的point "A,5"
     * @param edges "A->B,2" A 到 B的cost为2
     * @param src
     * @param path
     * @return
     */
    public int maxPath(String[] nodes, String[] edges, String src, List<String> path) {
        //相当于graph node里存着neighbors
        Map<String, Node> nodeByName = new HashMap<>() ;
        Map<String, Integer> indegrees = new HashMap<>() ;

        //
        for ( String s: nodes ) {
            //s = "A,5"
            String[] t = s.split(",") ;
            String name = t[0] ;
            int point = Integer.parseInt(t[1]) ;
            if ( ! nodeByName.containsKey(name) ) {
                nodeByName.put(name,  new Node(name, point));
            }
            nodeByName.get(name).point = point;
            //把每个点的indegree 设成0
            indegrees.put(name, 0);

            if ( name.equals(src) ) {
                nodeByName.get(name).score = 0 ; // Set src score to 0
            }
        }

        for ( String s: edges ) {
            //s = "A->B,2"  A到B的cost为2
            String[] t = s.split(",") ;
            String[] fromto = t[0].split("->") ;
            String from = fromto[0], to = fromto[1] ;
            int dist = Integer.parseInt(t[1]) ;
            Node fromNode = nodeByName.get(from);
            // 先check to在不在 fromNode的next里， 不在 to的入度+1
            if ( ! fromNode.nextsWithDistance.containsKey(to) ) {
                indegrees.put(to, indegrees.getOrDefault(to, 0) + 1) ;
            }
            //再把to 加到 from的next里去
            nodeByName.get(from).nextsWithDistance.put(to, dist) ;
        }

        // topological order
        Map<String, String> curToParant = new HashMap<>() ;
        Queue<Node> q = new LinkedList<>() ;
        for ( String name: indegrees.keySet() ) {
            if ( indegrees.get(name) == 0 ) q.offer(nodeByName.get(name)) ;
        }

        String maxScoreDestName = "" ;
        int ans = Integer.MIN_VALUE ;
        while ( ! q.isEmpty() ) {
            Node curr = q.poll() ;

            if ( curr.nextsWithDistance.isEmpty() // 到底部了 不能再往下滑了
                    && curr.score != Integer.MIN_VALUE  // 且能滑到这
                    &&  curr.score > ans) { //比glocal的ans 得分高
                ans = curr.score ;
                maxScoreDestName = curr.name ;
            }

            for ( String next: curr.nextsWithDistance.keySet() ) {
                if ( curr.score != Integer.MIN_VALUE ) { // 加这个以防溢出
                    int getToNextscore = getScore(curr.point, curr.nextsWithDistance.get(next)) ;

                    if ( curr.score + getToNextscore > nodeByName.get(next).score ) {
                        nodeByName.get(next).score = curr.score + getToNextscore ;
                        curToParant.put(next, curr.name) ;
                    }
                }

                indegrees.put(next, indegrees.get(next) - 1) ;
                if ( indegrees.get(next) == 0 ) {
                    q.offer(nodeByName.get(next)) ;
                }
            }
        }

        // Build path
        path.clear();
        while ( !maxScoreDestName.isEmpty() ) {
            path.add(maxScoreDestName) ;
            maxScoreDestName = curToParant.containsKey(maxScoreDestName) ? curToParant.get(maxScoreDestName) : "" ;
        }

        Collections.reverse(path);
        return ans ;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ski sol = new Ski() ;
        String[] nodes = {"A,5", "B,7", "C,6", "D,2", "E,4", "F,7", "H,7", "I,3", "J,2"} ;
        String[] edges = {
                "A->B,2",
                "A->C,3",
                "B->D,5",
                "B->E,6",
                "C->E,4",
                "C->F,4",
                "D->H,7",
                "E->H,6",
                "H->I,1",
                "H->J,2",
                "F->J,3"
        } ;
        List<String> path = new ArrayList<>() ;

        /**
         * max score = 62
         * [A, B, E, H, J]
         */
        int ans = sol.maxPath(nodes, edges, "A", path) ;
        System.out.println(ans);
        System.out.println(path);
    }

    static class DFS {
        private int maxScore = Integer.MIN_VALUE;
        //<from, <To, Cost>>
        private Map<String, Map<String, Integer>> pathMap; // graph info
        private Map<String, Integer> rewardMap;  //   graph info
        private Map<String, Integer> scoreMap;   //   dfs pruning
        private List<String> maxPath;

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

        public int findMaxScore(List<List<String>> paths, List<List<String>> rewards, String start) {
            this.pathMap = new HashMap<>();
            this.rewardMap = new HashMap<>();
            this.scoreMap = new HashMap<>();
            for (List<String> path : paths) {
                pathMap.putIfAbsent(path.get(0), new HashMap<>());
                pathMap.putIfAbsent(path.get(1), new HashMap<>());
                pathMap.get(path.get(0)).put(path.get(1), Integer.parseInt(path.get(2)));
            }
            for (List<String> point : rewards) {
                rewardMap.put(point.get(0), Integer.parseInt(point.get(1)));
            }
            Set<String> ends = new HashSet<>();
            for (String key : pathMap.keySet()) {
                scoreMap.put(key, 0);
                if (pathMap.get(key).isEmpty()) {
                    ends.add(key);
                }
            }
            List<String> curPath = new ArrayList<>();
            dfs(curPath, ends, start, 2 * rewardMap.get(start));
            return maxScore == Integer.MIN_VALUE ? -1 : maxScore;
        }

        private void dfs(List<String> curPath, Set<String> ends, String curr, int curScore) {
            curPath.add(curr);
            if (ends.contains(curr)) {
                if (curScore > this.maxScore) {
                    this.maxPath = new ArrayList<>(curPath);
                    this.maxScore = curScore;
                }
                curPath.remove(curPath.size() - 1);
                return;
            }
            for (Map.Entry<String, Integer> entry : pathMap.get(curr).entrySet()) {
                String next = entry.getKey();
                int newScore = curScore + 2 * rewardMap.get(next) + entry.getValue();;

                if (newScore <= scoreMap.get(next)) {
                    continue;
                }

                scoreMap.put(next, newScore);
                dfs(curPath, ends, next, newScore);
            }
            curPath.remove(curPath.size() - 1);
        }
    }
}
