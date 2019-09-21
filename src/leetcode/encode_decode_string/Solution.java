package leetcode.encode_decode_string;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Solution {
    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s : strs) {
            sb.append(s.length()).append('/').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        while(i < s.length()) {
            int slash = s.indexOf('/', i);
            int size = Integer.valueOf(s.substring(i, slash));
            i = slash + size + 1;
            ret.add(s.substring(slash + 1, i));
        }
        return ret;
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("hello");
        list.add("world");
        list.add("3/abc");

        String encoded = encode(list);
        System.out.println(encoded);

        System.out.println(decode(encoded));
    }
}
