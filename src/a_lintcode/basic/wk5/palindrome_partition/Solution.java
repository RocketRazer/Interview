package a_lintcode.basic.wk5.palindrome_partition;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }

        List<String> list = new ArrayList<>();
        dfs(0, list, result, s);

        return result;
    }

    void dfs(int startIndex, List<String> list, List<List<String>> result, String s) {
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(list));
        }

        for (int i = startIndex; i < s.length(); i++) {
            String sub = s.substring(startIndex, i + 1);
            if (!iPalindrome(sub)) {
                continue;
            }
            list.add(sub);
            dfs(i + 1, list, result, s);
            list.remove(list.size() - 1);
        }
    }

    boolean iPalindrome(String s) {
        int start = 0, end = s.length() -1;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return  true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> result = solution.partition("aab");
        result.stream().forEach(System.out::println);

    }
}
