package a_lintcode.improve.iw1.strstr;

public class Solution {
    /**
     * @param source:
     * @param target:
     * @return: return the index
     */
    public static int strStr(String source, String target) {
        if (source == null || source.length() == 0
                || target == null || target.length() == 0
                || source.length() < target.length()) {
            return -1;
        }

        char[] S = source.toCharArray();
        char[] T = target.toCharArray();

        int i, j;
        for (i = 0; i < S.length; i++) {
            for (j = 0; j < T.length; j++) {
                if (S[i] != T[j]) {
                    break;
                } else {
                    i++;
                }
            }

            if (j == T.length) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution.strStr("abcdedf", "bcd");
    }
}