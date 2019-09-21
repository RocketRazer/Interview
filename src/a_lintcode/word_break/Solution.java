package a_lintcode.word_break;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    /*
     * @param s: A string
     * @param dict: A dictionary of words dict
     * @return: A boolean
     */
    public static boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        Queue<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[s.length() + 1];

        queue.offer(0);
        visited[0] = true;


        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for (int i = cur + 1; i < s.length(); i++) {
                if (visited[i]) {
                    continue;
                }

                String str = s.substring(cur, i);
                if (dict.contains(str)) {
                    if (i == s.length()) {
                        return true;
                    }

                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//        set.add("a");
//        wordBreak("a", set);

        int res = (5 - 0) ^ 2 +  (2 -  0) ^ 2;
        int res2 = (5 - 0) * (5 - 0)  +  (2 -  0) * (2 -  0);


        System.out.println(res);
        System.out.println(res2);

    }
}