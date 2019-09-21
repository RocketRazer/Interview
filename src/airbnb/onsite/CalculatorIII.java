package airbnb.onsite;

import java.util.Stack;

public class CalculatorIII {

    public static int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        s += "+";
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int sum = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    sum = sum * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                num = sum;
            } else if (s.charAt(i) == '(') {
                // j is the close parenthese index matching open parenthese at i
                int j = i;
                int cnt = 0;
                for (; j < s.length(); j++) {
                    if (s.charAt(j) == '(') ++cnt;
                    if (s.charAt(j) == ')') --cnt;
                    if (cnt == 0) break;
                }
                num = calculate(s.substring(i+1, j));
                i = j;
            } else if (s.charAt(i) == '+'
                    || s.charAt(i) == '-'
                    || s.charAt(i) == '*'
                    || s.charAt(i) == '/' ) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }

                sign = s.charAt(i);
                num = 0;
            }
        }

        int res = 0;
        for (Integer i : stack) {
            res += i;
        }

        return res;

    }


    public static void main(String[] args) {
        System.out.println(calculate("2*(5+5*2)/3"));
    }
}
