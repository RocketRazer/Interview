package airbnb;

import java.util.*;

public class PyramidTransitionMatrix {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> map = new HashMap<>();

        for (String s : allowed) {
            char[] c = s.toCharArray();
            char first = c[0];
            char second= c[1];

            Set<Character> set = map.getOrDefault(String.valueOf(new char[]{first, second}), new HashSet<>());
            set.add(c[2]);
            map.put(String.valueOf(new char[]{first,second}), set);

            Set<Character> set2 = map.getOrDefault( String.valueOf(new char[]{second, first}), new HashSet<>());
            set2.add(c[2]);
            map.put(String.valueOf(new char[]{second,first}), set2);
        }

        // for (Map.Entry<String, Set<Character>> entry : map.entrySet()) {
        //     System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        // }

        return helper(bottom, "", map);
    }

    private boolean helper(String bottom, String top, Map<String, Set<Character>> map) {
        if (bottom.length() == 2 && top.length() == 1) return true;

        if (bottom.length() - top.length() == 1) return helper(top, "", map);

        int pos = top.length();
        String base = bottom.substring(pos, pos + 2);

        if (map.containsKey(base)) {
            for (Character c : map.get(base)) {
                if (helper(bottom, top + c, map)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
//        char a = 'a';
//        char b = 'b';
//        char[] array = new char[]{a, b};
//        System.out.println(String.valueOf(a ));
//        System.out.println(String.valueOf(b));
//        System.out.println(String.valueOf(new char[]{a, b}));


        String[] strings = {"ABC", "ABD","BCE","DEF","FFF"};

        PyramidTransitionMatrix p = new PyramidTransitionMatrix();
        System.out.println(p.pyramidTransition("ABC", Arrays.asList(strings)));

    }
}
