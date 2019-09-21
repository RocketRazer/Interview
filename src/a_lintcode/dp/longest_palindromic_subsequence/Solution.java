package a_lintcode.dp.longest_palindromic_subsequence;
public class Solution {
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    public static int longestPalindromeSubseq(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] C = s.toCharArray();

        int n = C.length;

        // f[i][j] represents the LPS from  C[i] to C[j]
        // f[i][j] = max { f[i + 1][j], f[i][j - 1], f[i + 1][j - 1] + 2 (if C[i] == C[j])}
        int[][] f = new int[n][n];

        // init len = 1
        for (int i = 0; i < n; i++) {
            f[i][i] = 1;
        }

        // init len = 2
        for (int i = 0; i < n -1; i++) {
            f[i][i+1] = C[i] == C[i+1] ? 2 : 1;
        }


        //len >= 3
        for (int len = 3; len <= n; len++) {
            for (int i = 3; i <= n - len; i++) {
                int j = i + len -1;
                // 去头或去尾
                f[i][j] = Math.max(f[i+1][j], f[i][j-1]);
                if(C[i] == C[j]) {
                    f[i][j] = Math.max(f[i][j], f[i+1][j-1] + 2);
                }
            }
        }


        return f[0][n-1];
    }

    public static void main(String[] args) {
        Solution.longestPalindromeSubseq("bbbab");
    }
}