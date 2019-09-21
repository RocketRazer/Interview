package a_lintcode.basic.wk5.word_ladder;

import java.util.*;

public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        if (dict == null || dict.size() == 0) {
            return 0;
        }

        if (start.equals(end)) {
            return 1;
        }

        dict.add(start);
        dict.add(end);

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        int length = 1;
        while (!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                List<String> nextWords = getNextWords(word, dict);

                for (String s : nextWords) {
                    if (visited.contains(s)) {
                        continue;
                    }

                    if (s.equals(start)) {
                        return length;
                    }

                    queue.offer(s);
                    visited.add(s);
                }
            }
        }

        return 0;
    }


    private List<String> getNextWords(String word, Set<String> dict) {
        List<String> result = new ArrayList<>();
        for(char c = 'a'; c <= 'z'; c++) {
            for (int j = 0; j < word.length(); j++) {
                if(word.charAt(j) == c) {
                    continue;
                }

                String nextWord = replaceWord(word, c, j);

                if (dict.contains(nextWord)) {
                    result.add(nextWord);
                }
            }
        }
        return result;
    }

    private String replaceWord(String word, char c, int index) {
        char[] chars = word.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.ladderLength("a", "c", new HashSet<>(Arrays.asList("a", "b"))));
    }
}
