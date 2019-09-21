package a_lintcode.improve.iw1.minimum_window_substring;

public class Solution {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public static String minWindow(String source , String target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException();
        }

        String res = "";

        if (source.length() == 0 || target.length() == 0) {
            return res;
        }

        int[] targetCMap = new int[256];
        for (int i = 0; i< target.length(); i++) {
            targetCMap[target.charAt(i)]++;
        }

        int curMinLen = Integer.MAX_VALUE;
        int targetCount = target.length();
        int sourceCount = 0;
        int j = 0;
        for (int i = 0; i < source.length(); i++) {
            while (j < source.length() && sourceCount < targetCount) {
                char c = source.charAt(j);
                targetCMap[c]--;
                if (targetCMap[c] >= 0) {
                    sourceCount++;
                }
                j++;
            }


            if (sourceCount == targetCount && curMinLen > j - i) {
                curMinLen = j - i;
                res = source.substring(i, j);
            }

            char c = source.charAt(i);
            targetCMap[c]++;
            if (targetCMap[c] > 0) {
                sourceCount--;
            }
        }

        return res;

    }

    public static void main(String[] args) {
        System.out.println(minWindow("abcde", "db"));
    }
}