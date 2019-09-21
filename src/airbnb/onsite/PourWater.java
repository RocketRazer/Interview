package airbnb.onsite;

public class PourWater {
    /**
     * 先往左再往右后中间
     * 左右有边界assume 无限高
     * @param heights
     * @param water
     * @param location
     */
    public void pourWater(int[] heights, int water, int location) {
        int[] waters = new int[heights.length];
        int pourLocation;

        while (water > 0) {
            // 先往左
            int left = location - 1;

            while (left >= 0) {
                //找到第一个左边比右边高的
                if (heights[left] + waters[left] > heights[left + 1] + waters[left + 1]) {
                    break;
                }
                left--;
            }

            // left+1 是第一个左边比右边高的右边的index，如果比pour location矮就把水倒在那
            if (heights[left + 1] + waters[left + 1] < heights[location] + waters[location]) {
                pourLocation = left + 1;
                waters[pourLocation]++;
                water--;
                continue;
            }

            //如果左边没法倒了，往右边找
            int right = location + 1;
            while (right < heights.length) {
                //找到第一个右边比左边高的
                if (heights[right] + waters[right] > heights[right - 1] + waters[right - 1]) {
                    break;
                }
                right++;
            }

            // right - 1 是第一个右边比左边高的左边的index，如果比pour location矮就把水倒在那
            if (heights[right - 1] + waters[right - 1] < heights[location] + waters[location]) {
                pourLocation = right - 1;
                waters[pourLocation]++;
                water--;
                continue;
            }

            //如果左右两遍都比pour location高，倒在pour location
            pourLocation = location;
            waters[pourLocation]++;
            water--;
        }

        print(heights, waters);
    }

    /**
     * 先左后右， 无边界
     * @param heights
     * @param water
     * @param location
     */
    public void pourWaterWithNoEdge(int[] heights, int water, int location) {
        int n = heights.length;
        int[] waterArr = new int[n];
        int pourLocation = location;

        while (water > 0) {
            //先check 左边
            int left = pourLocation -1;
            while (left >= 0) {
                if (heights[left] + waterArr[left] >  heights[left + 1] + waterArr[left +1]) {
                    break;
                }

                left--;
            }


            if (heights[left + 1] + waterArr[left +1] < heights[pourLocation] + waterArr[pourLocation]) {
                // left < 0 说明遍历到最左边了, 因为最左边的还比pour location 矮
                // water hold 不住，water 溜走
                if (left < 0) {
                    water--;
                    continue;
                } else {
                    //还没到最左边，可以hold水
                    waterArr[left+1]++;
                    water--;
                    continue;
                }
            }


            //check 右边
            int right = pourLocation + 1;
            while (right < n) {
                if (heights[right] + waterArr[right] > heights[right-1] + waterArr[right-1]) {
                    break;
                }
                right++;
            }

            if (heights[right-1] + waterArr[right-1] < heights[pourLocation] + waterArr[pourLocation]) {
                // right == n 说明遍历到最右边了, 因为最右边的还比pour location 矮
                // water hold 不住，water 溜走
                if (right == n) {
                    water--;
                    continue;
                } else {
                    //还没到最右边，可以hold水
                    waterArr[right+1]++;
                    water--;
                    continue;
                }
            }

            //中间比左右两边都矮， pour 中间
            waterArr[pourLocation]++;
            water--;
        }

        print(heights, waterArr);
    }


    /**
     * 打印
     * @param heights
     * @param waters
     */
    private void print(int[] heights, int[] waters) {
        int n = heights.length;

        int maxHeight = 0;
        //先找到水 + 地的最高点
        for (int i = 0; i < n; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }

        //遍历高度： 从最高层 往下一层一层打印
        for (int height = maxHeight; height >= 0; height--) {
            //遍历每个column
            for (int i = 0; i < n; i++) {
                //如果地大于等于当前高度 打印地
                if (height <= heights[i]) {
                    System.out.print("+");
                } else if (height > heights[i] && height <= heights[i] + waters[i]) {
                    //如果当前高度大于地 小于等于地加水 打印水
                    System.out.print("W");
                } else {
                    // 当前高度大于 地加水 打印空
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PourWater pw = new PourWater();
        int[] heights = {5,4,2,1,2,3,2,1,0,1,2,4};
        for (int i = 1; i <= 9; i++) {
            pw.pourWater(heights, i, 5);
        }

        pw.pourWater(heights, 14, 5);

        System.out.println("Pour water without edges， neither sides could hold water");
        int[] heights1 = {1,2,3,2,1};
        pw.pourWaterWithNoEdge(heights1, 10, 4);

        System.out.println("Pour water without edges， left could hold 1 water");
        int[] heights2 = {3,2,3,2,2};
        pw.pourWaterWithNoEdge(heights2, 10, 2);

        System.out.println("Pour water without edges， pour location start with lowest height");
        int[] heights3 = {3,2,1,2,2};
        pw.pourWaterWithNoEdge(heights3, 10, 2);

    }
}



