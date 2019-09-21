package a_lintcode.basic.wk2.divide_two_integer;

public class Solution {
    /**
     * @param dividend the dividend
     * @param divisor the divisor
     * @return the result
     */
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isNegative = (dividend > 0 && dividend < 0) || (dividend < 0 && dividend > 0);

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;
        while (a >= b) {
            int shift = 0;
            while (a >= (b << shift)) {
                shift++;
            }

            a = a - (b << (shift - 1));
            result += (1 << (shift -1));
        }

        return isNegative ? -result : result;
    }

    public static void main(String[] args) {
        Solution.divide(1, -1);

//        for (int i = 1; i < 10; i++) {
//            System.out.println(9 << i);
//        }
    }
}