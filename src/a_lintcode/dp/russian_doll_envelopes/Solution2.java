package a_lintcode.dp.russian_doll_envelopes;

import java.util.Arrays;
import java.util.Comparator;

public class Solution2 {
    /*
     * @param envelopes: a number of envelopes with widths and heights
     * @return: the maximum number of envelopes
     */
    public static int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(n == 0){
            return 0;
        }
        //第一维相等时第二维一定要降序，否则后面二分查找判断很麻烦
        Arrays.sort(envelopes, new Comparator<int[]>(){

            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            }
        });

        int[] b = new int[n + 1];
        b[0] = Integer.MIN_VALUE;
        int btop = 0, j = 0;
        for(int i = 0; i < n; i++){
            int mid;
            int start = 0;
            int end = btop;
            while (start <= end) {
                mid = (end + start) / 2;
                if (b[mid] < envelopes[i][1]) {
                    j = mid;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            b[j+1] = envelopes[i][1];

            if (j + 1 > btop ) {
                btop = j + 1;
            }
        }

        return btop;
    }

    public static void main(String[] args) {
        //[[5,4],[6,4],[6,7],[2,3]]
        int[][] env = new int[3][2];
        env[0] = new int[]{2,3};
        env[1] = new int[]{6,4};
        env[2] = new int[]{6,7};

        System.out.print(Solution2.maxEnvelopes(env));
    }
}
