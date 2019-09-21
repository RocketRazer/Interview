package a_lintcode.improve.iw1.longest_substring_with_distinct_k;

public class Solution {
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (k > s.length()) {
            return s.length();
        }

        int max = 0;

        int[] hash = new int[256];
        int j = 0;
        int distinct = 0;
        for (int i =0; i < s.length(); i++) {
            while (j < s.length()) {
                char c = s.charAt(j);
                if (distinct < k) {
                    if (hash[c] == 0) {
                        distinct++;
                    }
                    hash[c]++;
                    j++;
                } else {
                    break;
                }
            }



            char c = s.charAt(i);
            hash[c]--;
            if (hash[c] == 0) {
                distinct--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "eceba";
        Solution solu = new Solution();
        solu.lengthOfLongestSubstringKDistinct(s, 3);
//        char[] a = s.toCharArray();
//        Arrays.sort(a);
//        System.out.println(Arrays.toString(a));
    }
}
