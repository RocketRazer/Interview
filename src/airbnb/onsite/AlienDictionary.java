package airbnb.onsite;


import java.util.*;

/**
 *
 * 1. how to solve with DFS
 * 2. 如何找出所有可能方案
 * 3. What if has cycle
 */
public class AlienDictionary {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return null;

        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        buildGraph(words, indegree, graph);

        System.out.println(indegree);

        return bfs(words, indegree, graph);
    }



    private void buildGraph(String[] words, Map<Character, Integer> indegree, Map<Character, Set<Character>> graph) {
        for (String w : words) {
            for (Character c : w.toCharArray()) {
                indegree.putIfAbsent(c, 0);
                graph.putIfAbsent(c, new HashSet<>());
            }
        }


        for (int i = 0; i < words.length -1; i++) {
            String w1 = words[i];
            String w2 = words[i+1];

            int minLen = Math.min(w1.length(), w2.length());

            for (int j = 0; j < minLen; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    char from = w1.charAt(j);
                    char to = w2.charAt(j);

                    if (!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        indegree.put(to, indegree.get(to) + 1);
                    }
                    //这里只需要找到第一个不同的就行，后面的比较没有意义了
                    // 因为如果前面的已经不同了， 就不能判断后面的相对顺序了
                    break;
                }
            }
        }
    }


    private String bfs(String[] words, Map<Character, Integer> indegree, Map<Character, Set<Character>> graph) {
        //有个很小的坑, 就是当你的字典是["zy","zx"]的情况的时候, 最后的结果有可能不是字典序的(即输出可能是"zyx",正确应该是"yxz"
        // 因为哈希表是没有顺序的)为了处理这种情况, 我们在进行BFS的时候, 应该使用PrioirtyQueue来保证输出的顺序
        PriorityQueue<Character> q = new PriorityQueue<>();

        //1. find all character in the graph whose indegree is 0, add them to the queue
        for (Character c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                q.offer(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!q.isEmpty()) {
            Character cur = q.poll();
            res.append(cur);

            if (!graph.containsKey(cur) || graph.get(cur).size() == 0) {
                continue;
            }

            for (Character neighbor : graph.get(cur)) {
                int degree = indegree.get(neighbor) -1;
                indegree.put(neighbor, degree);

                if (degree == 0) {
                    q.offer(neighbor);
                }
            }
        }

        //3. if final res.length == g.size() return res else return ""
        return res.length() == graph.size() ? res.toString() : "";
    }


    public static void main(String[] args) {
        String[] words = {"za", "zb", "ca", "cb"};
        AlienDictionary s = new AlienDictionary();
        System.out.println(s.alienOrder(words));
    }
}