package airbnb.A;

import java.util.Random;

public class AShutTheBox {

    /**
     * boxes[0] ~ boxes[8] represent board  1 ~ 9
     */
    boolean[] boxes = new boolean[9];


    /**
     * shut two boxes
     * @param num1
     * @param num2
     * @return true if two boxes were shut successfully; false if any or both box already closed
     */
    public boolean shutBox(int num1, int num2) {

        if (!isValidNumber(num1) || !isValidNumber(num2) || num1 == num2) {
            return false;
        }

        if (!boxes[num1 - 1] && !boxes[num2 - 1]) {
            boxes[num1 - 1] = true;
            boxes[num2 - 1] = true;
            return  true;
        } else {
            return false;
        }
    }

    /**
     * shut single box
     * @param num1
     * @return true if the boxes was shut successfully; false if the box already shut;
     */
    public boolean shutBox(int num1) {
        // can not shut the box greater than 9
        if (!isValidNumber(num1)) {
            return false;
        }
        if (!boxes[num1 -1]) {
            boxes[num1 -1] = true;
            return true;
        }

        return false;
    }


    /**
     * check whether the play wins or not
     * @return true if all boxes are shut; false otherwise
     */
    public boolean winCheck() {
        for (boolean b : boxes) {
            if (!b) {
                return false;
            }
        }

        return true;
    }


    /**
     * try to shut the sum first, if can shut, just shut
     * if not, try shut two numbers, loop through the first number from 1 ~ 8, use sum - firstNumber for second number
     * if first number greater than sum, break directly
     * @param sum
     * @return
     */
    public boolean shutBox_strategy1(int sum) {
        boolean shutSum = shutBox(sum);
        if (shutSum) {
            return true;
        } else {
            for (int i = 1; i < 9; i++) {
                if (i >= sum) break;

                boolean shutTwo = shutBox(i, sum - i);
                if (shutTwo) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean isValidNumber(int num) {
        return num >= 1 && num <= 9;
    }


    public static boolean play() {
        AShutTheBox shutTheBox = new AShutTheBox();
        boolean win;

        Random dice1 = new Random();
        Random dice2 = new Random();
        while (true) {
            int roll1 = dice1.nextInt(6) + 1;
            int roll2 = dice2.nextInt(6) + 1;

            if (shutTheBox.shutBox_strategy1(roll1 + roll2)) {
                continue;
            } else {
                win = shutTheBox.winCheck();
                break;
            }
        }

        return win;
    }


    public static void main(String[] args) {
        int totalPlays = 10000;
        int winCount = 0;
        for (int i = 0; i < totalPlays; i++) {
            AShutTheBox shutTheBox = new AShutTheBox();
            if(shutTheBox.play()) {
                winCount++;
            }
        }
        double winRate = (winCount / (double)totalPlays) * 100;
        System.out.println(winCount);
        System.out.println("Win Rate is " + winRate  + "%" );
    }
}
