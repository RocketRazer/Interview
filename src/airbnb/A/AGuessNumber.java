package airbnb.A;

import airbnb.onsite.GuessNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AGuessNumber {
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
        int firstResp = checkNumberOfCorrectDigitsOnCorrectPosition(new String(base));
        if (firstResp == 4) {
            return base;
        }

        //初始化为0000
        char[] res = new char[4];
        Arrays.fill(res, '0');

        //循环4位数的每一位
        for (int i = 0; i < 4; i++) {
            int prevResp = firstResp;
            char[] charBase = base.toCharArray();

            //把这个位上的数换成2到5 check guess
            for (int j = 2; j < 6; j++) {
                charBase[i] = (char)('0' + j);
                int resp = checkNumberOfCorrectDigitsOnCorrectPosition(new String(charBase));

                if (resp == 4) {
                    return new String(charBase);
                }

                if (resp != prevResp) {
                    //如果check 当前位 （2 ～ 5）中的每一位，和之前"1111" 结果不一样
                    // 1. 比之前猜1111多猜对了一位，就说明这位就猜对了, 就是当前的j
                    // 2. 比之前猜1111少猜对了一位，就说明这位就是1
                    res[i] = resp > prevResp ? (char)('0' + j) : '1';
                    break;
                }
            }

            if (res[i] == '0') {
                //如果当前位猜完2-5都不对，说明这位就是6
                res[i] = '6';
            }
        }
        // 最坏情况猜 17次
        // 第一次 + 每一位猜4次
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

        System.out.println(gn.sendAndReceive("start"));
        System.out.println("Result: " + gn.guess());

        System.out.println(gn.sendAndReceive("start"));
        System.out.println("Result: " + gn.guess());

        System.out.println(gn.sendAndReceive("start"));
        System.out.println("Result: " + gn.guess());
    }
}
