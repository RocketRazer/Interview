package airbnb.A;

import airbnb.PyramidTransitionMatrix;

import java.util.*;

public class APyramid {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> allowedCharByBase = new HashMap<>();

        for (String s : allowed) {
            char[] char_arr = s.toCharArray();

            String base = String.valueOf(new char[]{char_arr[0], char_arr[1]});
            Set<Character> allowedChars = allowedCharByBase.getOrDefault(base, new HashSet<>());
            allowedChars.add(char_arr[2]);
            allowedCharByBase.put(base, allowedChars);
        }

        return dfs(bottom, "", allowedCharByBase);
    }

    private boolean dfs(String bottom, String top, Map<String, Set<Character>> allowedCharByBase) {
        if (bottom.length() == 2 && top.length() == 1) {
            return true;
        }

        if (bottom.length() - top.length() == 1) {
            return dfs(top, "", allowedCharByBase);
        }

        int basePos = top.length();
        String base = bottom.substring(basePos, basePos + 2);
        if (allowedCharByBase.containsKey(base)) {
            Set<Character> nextChars = allowedCharByBase.get(base);
            for (Character c : nextChars) {
                if (dfs(bottom, top + c, allowedCharByBase)) {
                    return  true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        String[] strings = {"ABC", "ABD","BCE","DEF","FFF"};

        APyramid p = new APyramid();
        System.out.println(p.pyramidTransition("ABC", Arrays.asList(strings)));
    }
}