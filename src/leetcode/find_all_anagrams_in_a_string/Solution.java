package leetcode.find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.List;


/**
 * 438. Find All Anagrams in a String
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        if (s == null || s.length() == 0) return res;

        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        int count = pc.length;

        int[] map = new int[126];

        for (Character c : pc) {
            map[c]++;
        }

        int left = 0, right = 0;
        while (right < s.length()) {
            map[sc[right]]--;
            if (map[sc[right]] >= 0) {
                count--;
            }

            if (count == 0 && right - left + 1 == p.length()) {
                res.add(left);
            }

            right++;

            if (right - left == p.length()) {
                if (map[sc[left]] >= 0) {
                    map[sc[left]]++;
                    left++;
                    count++;
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        Solution  s = new Solution();

        s.findAnagrams("cbaebabacd", "abc");
    }
}
