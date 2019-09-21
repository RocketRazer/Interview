package a_lintcode.dp.russian_doll_envelopes;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    /*
     * @param envelopes: a number of envelopes with widths and heights
     * @return: the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }
            }

        });

        int m = envelopes.length;
        int n = envelopes[0].length;
        int[] f = new int[m];
        int res = 0;
        for (int i = 0; i < m; i++) {
            f[i] = 1;

            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes [i][0] && envelopes[j][1] < envelopes [i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }

            res = Math.max(res , f[i]);
        }


        return res;
    }

    public static void main(String[] args) {
        int[][] nums = new int[4][2];
        nums[0] = new int[]{5,4};
        nums[1] = new int[]{6,4};
        nums[2] = new int[]{6,7};
        nums[3] = new int[]{2,3};

        Solution s = new Solution();
        System.out.println(s.maxEnvelopes(nums));

    }
}