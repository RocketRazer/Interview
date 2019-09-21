package amazon.competeNeighbor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<Integer> cellCompete(int[] states, int days)
    {
        int[] res = states;
        for (int i = 0; i < days; i++) {
            res = compete(res);
        }

        List<Integer> result = new ArrayList<>();
        for (int i : res) {
            result.add(i);
        }

        return result;
    }


    private int[] compete(int[] states) {
        int[] res = new int[states.length];

        for (int i =0 ; i< states.length; i++) {
            int left = i - 1 >= 0 ? states[i-1] : 0;
            int right = i + 1 <states.length ? states[i+1] : 0;

            res[i] = left == right ? 0 : 1;
        }

        return res;
    }
    // METHOD SIGNATURE ENDS


    public static void main(String[] args) {
        Solution s = new Solution();
        s.cellCompete(new int[]{1,0,0,0,0, 1,0,0}, 1);


        System.out.println(s.generalizedGCD(5, new int[]{2, 3, 4, 5, 6} ));
    }



    public int generalizedGCD(int num, int[] arr)
    {
        // WRITE YOUR CODE HERE
        Arrays.sort(arr);

        int min = arr[0];
        int res = 1;

        for (int i = 1; i <= min; i++) {

            for (int j = 0; j < arr.length; j++) {
                if (arr[j] % i != 0) {
                    break;
                } else if (j == arr.length -1) {
                    System.out.println(i);
                    res = i;
                }
            }
        }

        return res;
    }
}