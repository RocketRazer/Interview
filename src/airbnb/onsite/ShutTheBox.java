package airbnb.onsite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class ShutTheBox {

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

        if (!(num1 >= 1 && num1 <= 9 && num2 >= 1 && num2 <= 9) || num1 == num2) {
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
        if (!(num1 >= 1 && num1 <= 9)) {
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


    public static boolean play() {
        ShutTheBox shutTheBox = new ShutTheBox();
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
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        Integer totalPlays = null;
        System.out.println("Shut the Box game simulator start!");
        System.out.println();
        System.out.println("Please enter the total number of plays: ");


        while (totalPlays == null) {
            try {
                totalPlays = Integer.parseInt(in.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Input is not a valid number, please enter again: ");
            }
        }


        int numOfWin = 0;
        for (int i = 0; i < totalPlays; i++) {
            if (play()) {
                numOfWin++;
            }
        }

        System.out.println("Total number of plays [" + totalPlays + "]");
        System.out.println("Total number of wins [" + numOfWin + "]");
        System.out.println("Win rate: " + (double)numOfWin * 100 / (double) totalPlays + "%");

    }

}
