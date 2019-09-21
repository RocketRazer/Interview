package airbnb.onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Connected to a server, there is a number from 1111 to 6666
 * Send your guess to the server, it will return you with how many numbers are correct and on the
 * right position, how many numbers are correct but on the wrong position
 * Guess as less as possible
 *
 * For example
 * correct code: 3264
 * GUESS 1111 => 0 0 (no correct digits)
 * GUESS 1214 => 2 0 (digits 2 and 4 are correct and on correct position)
 * GUESS 6111 => 0 1 (digit 6 is present, but on a different position)
 * GUESS 6211 => 1 1 (digit 2 is not counted towards the second count!)
 *
 * Solution
 * 从1111开始猜，每次改变1位 (1-5)，比如以最高位为例，第一个Iteration猜 2111，3111，4111，5111
 * 如果改一个数字正确的变少了，说明这一位就是1
 * 如果改一个数字正确的变多了，说明这一位就是你现在猜的数字
 * 如果正确的数字一直是一样的，说明这一位是6
 *
 * worst case是6666，最多需要猜
 * 1111
 * 2111，3111，4111，5111
 * 1211，1311，1411，1511
 * 1121，1131，1141，1151
 * 1112，1113，1114，1115
 * 共17次
 *
 * 参考 http://www.1point3acres.com/bbs/thread-290126-1-1.html
 *
 * https://leetcode.com/problems/bulls-and-cows/
 */


public class GuessNumber {
    /**
     * guess count
     */
    private int count = 0;

    /**
     * number to guess 4 digits
     */
    private List<Integer> target = new ArrayList<>();

    /**
     * re-generate a random number
     * Simulation method, to generate or reset the random number, don't have to focus on it
     */
    public void reset() {
        target.clear();
        for (int i = 0; i < 4; ++i) {
            target.add((int)(Math.random() * 6) + 1);
        }
        count = 0;
    }

    /**
     * check method. Simulation method, don't have to focus it
     * @param str
     * @return a + " " + b
     */
    public String sendAndReceive(String str) {
        if (str.toLowerCase().equals("start")) {
            reset();
            return "Ready, target # is " + target.get(0) + target.get(1) + target.get(2) + target.get(3);
        }
        System.out.println("Times of method call: " + ++count + ", coming number: " + str);
        int a = 0;
        List<Integer> copyOfTarget = new ArrayList<>(target);
        List<Integer> t = new ArrayList<>();
        List<Integer> g = new ArrayList<>();

        for (int i = 0; i < 4; ++i) {
            int digit = copyOfTarget.get(i);
            char c = str.charAt(i);

            if (digit == c - '0') {
                ++a;
            } else {
                t.add(digit);
                g.add(c - '0');
            }
        }

        int size = g.size();
        g.removeAll(t);
        int b = size - g.size();

        System.out.println(a + " numbers are on correct and on the correct postion. " + b + " numbers are correct but on thr wrong position.");
        return a + " " + b;
    }

    public String guess() {
        String base = "1111";

        int firstResp = checkNumberOfCorrectDigitsOnCorrectPosition(base);
        if (firstResp == 4) {
            return base;
        }


        char[] res = new char[4];
        Arrays.fill(res, '0');

        // loop through every digit
        for (int i = 0; i < 4; i++) {
            int prevResp = firstResp;

            for (char c = '2'; c <= '5'; c++) {
                char[] baseArr = base.toCharArray();
                baseArr[i] = c;
                int currentResp = checkNumberOfCorrectDigitsOnCorrectPosition(new String(baseArr));

                if (currentResp == 4) {
                    return new String(baseArr);
                }

                if (currentResp > prevResp) {
                    res[i] = c;
                    break;
                } else if (currentResp < prevResp) {
                    res[i] = '1';
                    break;
                }
            }

            if (res[i] == '0') {
                res[i] = '6';
            }
        }

        return new String(res);
    }


    private int checkNumberOfCorrectDigitsOnCorrectPosition(String guess) {
       String result =  sendAndReceive(guess);
       return Integer.parseInt(result.split(" ")[0]);
    }

    public static void main(String[] args) {
        GuessNumber gn = new GuessNumber();
        System.out.println(gn.sendAndReceive("start"));
        System.out.println("Result: " + gn.guess());

        System.out.println();

        System.out.println(gn.sendAndReceive("start"));
        System.out.println("Result: " + gn.guess());
        System.out.println();

        System.out.println(gn.sendAndReceive("start"));
        System.out.println("Result: " + gn.guess());
        System.out.println();

        System.out.println(gn.sendAndReceive("start"));
        System.out.println("Result: " + gn.guess());
        System.out.println();
    }
}

