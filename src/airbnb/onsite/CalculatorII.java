package airbnb.onsite;

import java.util.Stack;

public class CalculatorII {
    public static int calculate(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        char sign = '+';

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
            }


            if (s.charAt(i) == '+'
                    || s.charAt(i) == '-'
                    || s.charAt(i) == '*'
                    || s.charAt(i) == '/'
                    || i == s.length() - 1) {
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
        //System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
    }
}
