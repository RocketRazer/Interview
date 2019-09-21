package a_lintcode.improve.iw7.submatrix_sum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ResultType {
    boolean flag = false;
    List<Integer> indices;
    public ResultType (boolean F, List<Integer> idx){
        this.flag = F;
        this.indices = idx;
    }
}
public class Solution {
    /*
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        // write your code here
        //预处理列向量和
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] col_sum = new int[m + 1][n];
        for (int i = 1; i <= m; i++){
            for (int j = 0; j < n; j++){
                col_sum[i][j] = col_sum[i - 1][j] + matrix[i - 1][j];
            }
        }

        int[] prefix = new int[n];
        //枚举起始行和终止行
        for (int start = 0; start < m; start++){
            for (int end = start; end < m; end++){
                //求start -> end这一段 所有列向量和
                for (int k = 0; k < n; k++){
                    prefix[k] = col_sum[end + 1][k] - col_sum[start][k];
                }
                //丢给subarray sum处理求index
                ResultType res = subarraySum(prefix);
                if (res.flag){
                    int[][] soln = {{start, res.indices.get(0)},{end, res.indices.get(1)}};
                    return soln;
                }
            }
        }
        int[][] soln = new int[2][2];
        return soln;
    }

    //subarray sum那道题抄过来的
    public ResultType subarraySum(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap <> ();
        map.put(0, 0);
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            if (map.containsKey(sum)){
                ans.add(map.get(sum));
                ans.add(i);
                return new ResultType(true, ans);
            }
            map.put(sum, i + 1);
        }
        return new ResultType(false, ans);
    }

    public static void main(String[] args) {
        int[][] nums = new int[3][3];
        nums[0] = new int[]{1, 5, 7};
        nums[1] = new int[]{3, 7, -8};
        nums[2] = new int[]{4, -8 ,9};

        Solution s = new Solution();
        s.submatrixSum(nums);
    }

}