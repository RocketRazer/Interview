package airbnb.onsite;

import java.util.Stack;

/**
 *
 * Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * 我们需要一个栈来辅助计算，用个变量sign来表示当前的符号，我们遍历给定的字符串s，
 * 如果遇到了数字，由于可能是个多位数，所以我们要用while循环把之后的数字都读进来，
 * 然后用sign*num来更新结果res；如果遇到了加号，则sign赋为1，如果遇到了符号，则赋为-1；
 * 如果遇到了左括号，则把当前结果res和符号sign压入栈，res重置为0，sign重置为1；如果遇到了右括号，
 * 结果res乘以栈顶的符号，栈顶元素出栈，结果res加上栈顶的数字，栈顶元素出栈。

 */
public class CalculatorI {

    public int caculate(String s) {
        if (s == null || s.length() ==0) {
            return 0;
        }

        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int sign = 1;


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int sum = c - '0';
                while (i < s.length() && Character.isDigit(s.charAt(i+1))) {
                    sum = sum * 10 + s.charAt(i+1) - '0';
                    i++;
                }
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            } else if (c == ')') {
                int preSign = stack.pop();
                int preResult = stack.pop();

                res = preResult + preSign * res;
            }
        }

        return res;
    }
}
