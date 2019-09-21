package a_google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxMoneyPath {
    public List<List<Integer>> maxMoney(int[][] moneys) {
        // assume: moneys is not null, width and length are equal
        int n = moneys.length;
        if (n == 0)
            return new ArrayList<>();
        // base case
        for (int j = 1; j < n; j++) {
            moneys[0][j] = -(Math.abs(moneys[0][j-1]) + moneys[0][j]);
        }
        for (int i = 1 ; i < n ; i++) {
            moneys[i][0] = moneys[i-1][0] + moneys[i][0];
        }
        for(int i = 1; i < n ; i++) {
            for(int j = 1; j < n ; j++) {
                int top = Math.abs(moneys[i-1][j]) + moneys[i][j];
                int left = Math.abs(moneys[i][j-1]) + moneys[i][j];
                //如果从上边来就是正数
                if(top >= left) moneys[i][j] = top;
                //从左边来，设为负数
                else moneys[i][j] = -left;
            }
        }

        System.out.println("Max path sum = " + Math.abs(moneys[n - 1][n - 1]));
        List<List<Integer>> path = new ArrayList<>();
        int curri = n-1;
        int currj = n-1;
        while (curri > 0 || currj > 0) {
            path.add(Arrays.asList(curri, currj));
            if(moneys[curri][currj] < 0) {
                currj -= 1;
            } else {
                curri -=1;
            }
        }
        path.add(Arrays.asList(0, 0));
        return path;
    }

}
