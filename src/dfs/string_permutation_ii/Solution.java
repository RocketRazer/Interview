package dfs.string_permutation_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String str) {
        res = new ArrayList<>();

        if (str == null) return res;

        if (str.length() == 0) {
            res.add("");
            return res;
        }

        charArray = str.toCharArray();
        Arrays.sort(charArray);
        visited = new boolean[charArray.length];

        List<Character> list = new ArrayList<>();
        dfs(list);

        return res;
    }

    List<String> res;
    char[] charArray;
    boolean[] visited;

    private void dfs(List<Character> list) {
        if (list.size() == charArray.length) {
            res.add(convertToString(new ArrayList<>(list)));
            return;
        }

        for (int i = 0; i < charArray.length; i++) {
            if(visited[i]) {
                continue;
            }

            if (i > 0 && !visited[i-1] && visited[i] == visited[i-1]){
                continue;
            }

            list.add(charArray[i]);
            visited[i] = true;
            dfs(list);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }



    private String convertToString(List<Character> list) {
        StringBuilder sb = new StringBuilder();
        for(Character c : list) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String a ="aab";
        Solution s = new Solution();
        System.out.println(s.stringPermutation2(a));
    }
}
