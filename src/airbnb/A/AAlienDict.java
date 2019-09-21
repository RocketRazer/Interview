package airbnb.A;

import airbnb.onsite.AlienDictionary;

import java.util.*;

/**
 *
   here is a new alien language which uses the latin alphabet.
 However, the order among letters are unknown to you.
 You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 Derive the order of letters in this language.

 Example 1:

 Input:
 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]

 Output: "wertf"


 You may assume all letters are in lowercase.
 You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.
 */
public class AAlienDict {
    private Map<Character, Set<Character>> graph;
    private Map<Character, Integer> indegrees;
    public String alienOrder(String[] words) {
        graph = new HashMap<>();
        indegrees = new HashMap<>();
        
        buildMaps(words);

        StringBuilder sb = new StringBuilder();

        PriorityQueue<Character> pq = new PriorityQueue<>();
        for (Character c : indegrees.keySet()) {
            if (indegrees.get(c) == 0) {
                pq.offer(c);
            }
        }

        while (!pq.isEmpty()) {
            Character current = pq.poll();
            sb.append(current);

            if (!graph.containsKey(current) || indegrees.get(current) == 0) {
                continue;
            }

            Set<Character> neighbors = graph.get(current);
            for (Character next : neighbors) {
                int updatedIndegree = indegrees.get(next) - 1;

                if (updatedIndegree == 0) {
                    pq.offer(next);
                }
            }
        }

        return sb.length() == graph.size() ? sb.toString() : "";
        
    }

    private void buildMaps(String[] words) {
        for (String w : words) {
            for (char c : w.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegrees.putIfAbsent(c, 0);
            }
        }
        
        
        for (int i = 0; i < words.length -1; i++) {
            int minLen = Math.min(words[i].length(), words[i+1].length());
            
            for (int j = 0; j < minLen; j++) {
                if (words[i].charAt(j) != words[i+1].charAt(j)) {
                    char from = words[i].charAt(j);
                    char to = words[i+1].charAt(j);

                    Set<Character> neighbors =  graph.get(from);
                    if (!neighbors.contains(to)) {
                        neighbors.add(to);
                        graph.put(from, neighbors);
                        indegrees.put(to, indegrees.get(to) + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"za", "zb", "ca", "cb"};
        AlienDictionary s = new AlienDictionary();
        System.out.println(s.alienOrder(words));
    }
}
