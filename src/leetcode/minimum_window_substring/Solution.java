package leetcode.minimum_window_substring;

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return null;
        }

        if(t.length() == 0) {
            return "";
        }

        String res = null;
        int minLen = Integer.MAX_VALUE;


        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        int[] map = new int[256];
        for (Character c : tc){
            map[c]++;
        }

        int left = 0, right = 0, count = tc.length;

        while (right < sc.length) {
            char right_c = sc[right];

            if (map[right_c] > 0) {
                count--;
            }

            map[right_c]--;

            while (count == 0) {
                String str = s.substring(left, right + 1);
                if (str.length() < minLen) {
                    minLen = str.length();
                    res = str;
                }



                char left_c = sc[left];
                // move left to right, see if we can find shorter
                if (map[left_c] >= 0) {
                    //left_c is in t
                    count++;
                }
                map[left_c]++;
                left++;
            }


            right++;
        }

        return res;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC"));
    }
}
