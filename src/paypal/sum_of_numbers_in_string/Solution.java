package paypal.sum_of_numbers_in_string;

public class Solution {

    private  int sumOfNumbers(String s) {
        char[] c = s.toCharArray();
        int sum = 0;

        int cur = 0;

        for (int i = 0; i < c.length; i++) {
            if(Character.isDigit(c[i])) {
                cur = cur*10 + Integer.parseInt(c[i] + "");
            } else {
                sum += cur;
                cur = 0;
            }
        }

        if (cur != 0) {
            sum += cur;
        }

        return sum;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.sumOfNumbers("1abc2x30yz67"));
        System.out.println(s.sumOfNumbers("1abc23"));
        System.out.println(s.sumOfNumbers("geeks4geeks"));
        System.out.println(s.sumOfNumbers("123abc"));

    }
}
