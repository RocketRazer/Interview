package a_google;

import java.util.*;

/**
 * 给定一个矩形的宽和长，求所有可能的路径数量

 Rules：
 1. 从左上角走到右上角
 2. 要求每一步只能向正右、右上或右下走，即 →↗↘

 followup1: 优化空间复杂度至 O(n)
 followup2: 给定矩形里的三个点，判断是否存在遍历这三个点的路经
 followup3: 给定矩形里的三个点，找到遍历这三个点的所有路径数量
 followup4: 给定一个下界 (x == H)，找到能经过给定下界的所有路径数量 (x >= H)
 followup5: 起点和终点改成从左上到左下，每一步只能 ↓↘↙，求所有可能的路径数量
 */
public class RobotPath {
    /**
     *
     * dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + dp[i + 1][j - 1]
     */
    public static int uniquePath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 ) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                }

                // dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + dp[i + 1][j - 1]

                if (j > 0) {
                    if (i > 0) {
                        dp[i][j] += dp[i - 1][j - 1];
                    }
                    dp[i][j] += dp[i][j - 1];

                    if (i + 1 < m) {
                        dp[i][j] += dp[i + 1][j - 1];
                    }
                }

            }

        }

        return dp[0][n-1];
    }

    /**
     * follow up 1: 滚动数组
     * Space: O(2n)
     */
    public static int uniquePath_rollingArray(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 ) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[] dp = new int[m];
        int[] tmp = new int[m];

        dp[0] = 1;

        for (int j = 1; j < n; j++) {
            tmp = Arrays.copyOf(dp, dp.length);
            for (int i = 0; i < m; i++) {
                // dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + dp[i + 1][j - 1]
                int leftTop = i > 0 ? tmp[i-1] : 0;
                int left =  tmp[i];
                int leftBottom = i + 1 < m ? tmp[i+1] : 0;
                dp[i] = leftTop + left + leftBottom;
            }
        }

        return dp[0];
    }


    /**
     * followup2: 给定矩形里的三个点，判断是否存在遍历这三个点的路径 (只能往右边，右上和右下走)
     * 假设三个点坐标为(x1, y1) (x2, y2) (x3, y3)
     * 1：从(0,0)出发，如果后一个点在前一个点展开的扇形区域内，则可以被达到
     * 2：先对三个点按照纵坐标y排序，如果一个y上有一个以上的点，则返回false
     * 3： 因为走法是 只能往右边，右上和右下走， 每次必向右走一步. 所以两个点的行数之差必须小于等于列数之差
     */
    public boolean pathExitsWith3Points(int[][] points) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[] {0, 0});
        for(int[] point : points) list.add(point);
        Collections.sort(list, (a, b) -> {
            return a[1] - b[1];
        });


        for(int i = 1 ; i < list.size() ; i++) {
            int[] curr = list.get(i);
            int[] prev = list.get(i-1);
            // 此处的判断不够严谨；还有一种情况是，两个点在同一列上，但这两个点其实是同一个点，则我们不应该直接false
            if(curr[1] == prev[1] && curr[0] != prev[0]) {
                return false;
            }

            int len = curr[1] - prev[1]; // 两个点的列数之差
            // 因为走法是 只能往右边，右上和右下走， 每次必向右走一步
            // 所以两个点的行数之差必须小于等于列数之差
            if(Math.abs(prev[0] - curr[0]) <= len) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * followup3: 给定矩形里的三个点，找到遍历这三个点的所有路径数量
     * 思路：
     1：用follow up 2 check 有没有path
     2：还是按照follow up 1的思路用滚动数组dp，但是如果当前列有需要到达的点时，
     只对该点进行dp，其他格子全部置零，表示我们只care这一列上经过目标点的路径
     */
    public int uniquePathThrough3Points(int rows, int cols, int[][] points) {
        int[] dp = new int[rows];
        int[] tmp = new int[rows];

        if (!pathExitsWith3Points(points)) {
            return 0;
        }

        // Map<y, x>
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] point : points) {
            map.put(point[1], point[0]);
        }

        int res = 0;
        dp[0] = 1;

        for(int j = 1 ; j  < cols ; j++) {
            tmp = Arrays.copyOf(dp, dp.length);
            for (int i = 0; i < rows; i++) {
                // dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + dp[i + 1][j - 1]
                int leftTop = i > 0 ? tmp[i-1] : 0;
                int left =  tmp[i];
                int leftBottom = i + 1 < rows ? tmp[i+1] : 0;
                dp[i] = leftTop + left + leftBottom;
            }

            //如果当前列有点，只对该点进行dp，其他格子全部置零，表示我们只care这一列上经过目标点的路径
            if(map.containsKey(j)) {
                int row = map.get(j);
                for(int i = 0 ; i < rows ; i++) {
                    if(i != row) {
                        dp[i] = 0;
                    } else {
                        res = dp[i];
                    }
                }
            }
        }
        return res;

    }


    /**
     * followup4: 给定一个下界
     (x == H)，找到能经过给定下界的所有从左上到右上的路径数量 (x >= H)

     * 思路：
     1：先dp一遍，得到所有到右上的路径数量
     2：然后在 0<=x<=H, 0<=y<=cols 这个小矩形中再DP一遍得到不经过下界的所有路径数量
     3：两个结果相减得到最终路径数量
     Code
     重用follow up 1的代码
     public int uniquePaths(int rows, int cols, int H) {
     return uniquePaths(rows, cols) - uniquePaths(H, cols);
     }

     */


    /**
     * followup5: 起点和终点改成从左上到左下，每一步只能 ↓↘↙，求所有可能的路径数量
     * 参考代码：按照 行 dp，其他地方不变
     */

    public int uniquePaths_topLeft_to_bottom_left(int rows, int cols) {
        int[] dp = new int[cols];
        int[] tmp = new int[cols];
        dp[0] = 1;
        for(int i = 1 ; i  < rows ; i++) {
            for(int j = 0 ; j < cols ; j++) {
                int val1 = j - 1 >= 0 ? dp[j - 1] : 0;
                int val2 = dp[j];
                int val3 = j + 1 < cols ? dp[j + 1] : 0;
                tmp[i] = val1 + val2 + val3;
            }
            System.arraycopy(tmp, 0, dp, 0, tmp.length);
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int[][] matrix_1 = new int[3][4];
        System.out.println(uniquePath_rollingArray(matrix_1));

    }
}
