package a_lintcode.basic.wk8.decode_string;

import java.util.Stack;

public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // write your code here
        StringBuilder result = new StringBuilder();
        if (s == null || s.length() == 0) {
            return result.toString();
        }

        Stack<Integer> repeat = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    count = 10 * count + s.charAt(i) - '0';
                    i++;
                }
                repeat.push(count);
            } else if (s.charAt(i) == '[') {
                stringStack.push(result);
                result = new StringBuilder();
                i++;
            } else if (s.charAt(i) == ']') {
                StringBuilder previousString = stringStack.pop();
                int repeatTime = repeat.pop();
                for (int j = 0; j < repeatTime; j++) {
                    previousString.append(result);
                }
                result = previousString;
                i++;
            } else { // c is string character
                result.append(s.charAt(i));
                i++;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.expressionExpand("3[2[ad]3[pf]]xyz");
    }
}