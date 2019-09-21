package airbnb.A;

import java.util.*;

/**
 * 每个人都有一个preference的排序，在不违反每个人的preference的情况下得到总体的preference的排序
 *
 * For example:
 * a: 2, 3, 5
 * b: 4, 2, 1
 * c: 4, 1, 5, 6
 * d: 4, 7
 *
 * Return:
 * 4, 2, 7, 3, 1, 5, 6
 *
 * 拓扑排序解决
 * (follow up break tie with person1)
 */
public class BPreferenceList {
    public List<Integer> sortPreference(List<List<Integer>> preferences, int tieBreaker) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();

        for (int i = 0; i < preferences.size(); i++) {
            for (int j = 0; j < preferences.get(i).size(); j++) {
                int thisNode = preferences.get(i).get(j);

                if (!edges.containsKey(thisNode)) {
                    edges.put(thisNode, new HashSet<>());
                    indegrees.put(thisNode, 0);
                }

                if (j > 0) {
                    int preNode = preferences.get(i).get(j - 1);
                    edges.get(preNode).add(thisNode);
                    indegrees.put(thisNode, indegrees.get(thisNode) + 1);
                }
            }
        }

        Set<Integer> tieBreakList = new HashSet<>(preferences.get(tieBreaker));

        Queue<Integer> queue = new LinkedList<>();
        for (int node : indegrees.keySet()) {
            List<Integer> tieBreakerPrefs = new ArrayList<>();
            List<Integer> othersPrefs = new ArrayList<>();
            if (indegrees.get(node) == 0) {
                if (tieBreakList.contains(node)) {
                    tieBreakerPrefs.add(node);
                } else {
                    othersPrefs.add(node);
                }
            }
            for (int pref : tieBreakerPrefs) {
                queue.offer(pref);
            }
            for (int pref : othersPrefs) {
                queue.offer(pref);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            /**
             * 遍历每一层的nodes的时候，不直接把下一层的node加到queue里
             * 想存到两个list里去，一个存tieBreaker list,另一个存 otherlist里
             * 在遍历完这一层之后，再把两个list依次加入到queue里去
             */
            List<Integer> tieBreakerPrefs = new ArrayList<>();
            List<Integer> othersPrefs = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                res.add(node);
                for (int next : edges.get(node)) {
                    indegrees.put(next, indegrees.get(next) - 1);
                    if (indegrees.get(next) == 0) {
                        if (tieBreakList.contains(next)) {
                            tieBreakerPrefs.add(next);
                        } else {
                            othersPrefs.add(next);
                        }
                    }
                }
            }

            for (int pref : tieBreakerPrefs) {
                queue.offer(pref);
            }
            for (int pref : othersPrefs) {
                queue.offer(pref);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        BPreferenceList pl = new BPreferenceList();
        List<List<Integer>> preferences = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        p1.add(2);
        p1.add(3);
        p1.add(5);
        List<Integer> p2 = new ArrayList<>();
        p2.add(4);
        p2.add(2);
        p2.add(1);
        List<Integer> p3 = new ArrayList<>();
        p3.add(4);
        p3.add(1);
        p3.add(5);
        p3.add(6);
        List<Integer> p4 = new ArrayList<>();
        p4.add(4);
        p4.add(7);
        preferences.add(p1);
        preferences.add(p2);
        preferences.add(p3);
        preferences.add(p4);

        //[4, 2, 7, 3, 1, 5, 6]
        System.out.println(pl.sortPreference(preferences, 0));
    }
}

