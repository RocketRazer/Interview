package airbnb.A;

/**
Assumption:
        1. input is array of integer, represents the height of ground
        2. print the graph after water drop
        3. water will go out from left side and right side
        4. water first go left to drop, if can not find a place, go right
        5. otherwise, drop at original place

        Simulate the drop one by one
        ##wwwww###
        ###w##w###
        ##########
        ##########

        Time: O(nk) + O(nh)
        k is the number of drop, n is the length of the array.
        drop water is O(nk), print graph takes O(nh) where h is the max number of the array
        Space: O(n) to store the water
*/
public class APourWater {
    public static void print (int[] heights, int[] waters) {
        int n = heights.length;
        int maxHeight = 0;
        // find max height
        for (int i = 0; i < n; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }

        // print
        for (int i = maxHeight; i > 0; i--) {
            int curHeight = i;
            for (int j = 0; j < n; j++) {
                if (heights[j] >= curHeight) {
                    System.out.printf("#");
                } else if (curHeight > heights[j] && curHeight <= heights[j] + waters[j]) {
                    System.out.printf("W");
                } else {
                    System.out.printf(" ");
                }
            }
            System.out.println();
        }
    }

    public void pourWater(int[] heights, int drops, int pourLocation) {
        int n = heights.length;
        int[] waters = new int[n];


        while (drops > 0) {
            int left = pourLocation - 1;
            
            // find the first left height > left + 1 height
            while (left > 0) {
                if (heights[left] + waters[left] > heights[left + 1] + waters[left + 1]) {
                    break;
                }
                left--;
            }
            
            // if the pos lower than the pour position
            if (heights[left + 1] + waters[left + 1] < heights[pourLocation] + waters[pourLocation]) {
                int dropPos = left + 1;
                waters[dropPos]++;
                drops--;
                continue;
            }


            // check right side
            int right = pourLocation + 1;
            while (right < n) {
                if (heights[right] + waters[right] > heights[right - 1] + waters[right -1]) {
                    break;
                }
                right++;
            }
            // if the pos lower than the pour position
            if (heights[right - 1] + waters[right -1] < heights[pourLocation] + waters[pourLocation]) {
                int dropPos = right -1;
                waters[dropPos]++;
                drops--;
                continue;
            }
            

            // drop at the pour location
            int dropPos = pourLocation;
            waters[dropPos]++;
            drops--;
        }

        print(heights, waters);

    }

    public void pourWaterWithNoEdge(int[] heights, int drops, int pourLocation) {
        int n = heights.length;
        int[] waters = new int[n];

        while (drops > 0) {

            // check left
            int left = pourLocation - 1;
            while (left >= 0) {
                if (heights[left] + waters[left] > heights[left + 1] + waters[left + 1]) {
                    break;
                }
                left--;
            }

            if (heights[left + 1] + waters[left + 1] < heights[pourLocation] + waters[pourLocation]) {
                if (left + 1 == 0) {
                    drops--;
                } else {
                    int dropPos = left + 1;
                    waters[dropPos]++;
                    dropPos--;
                }
                continue;
            }


            // check right

            int right = pourLocation + 1;
            while (right < n) {
                if (heights[right] + waters[right] > heights[right - 1] + waters[right -1]) {
                    break;
                }
                right++;
            }

            if (heights[right - 1] + waters[right -1] < heights[pourLocation] + waters[pourLocation]) {
                // right most point
                if (right - 1 == n - 1) {
                    drops--;
                } else {
                    int dropPos = right - 1;
                    waters[dropPos]++;
                    dropPos--;
                }
                continue;
            }


            int dropPos = pourLocation;
            waters[dropPos]++;
            drops--;
        }
        print(heights, waters);
    }

    public static void main(String[] args) {
        APourWater pw = new APourWater();
        int[] heights = {5,4,2,1,2,3,2,1,0,1,2,4};
        for (int i = 1; i <= 9; i++) {
            pw.pourWater(heights, i, 5);
        }

        pw.pourWater(heights, 14, 5);

        System.out.println("Pour water without edges， neither sides could hold water");
        int[] heights1 = {1,2,3,2,1};
        pw.pourWaterWithNoEdge(heights1, 10, 2);

        System.out.println("Pour water without edges， left could hold 1 water");
        int[] heights2 = {3,2,3,2,2};
        pw.pourWaterWithNoEdge(heights2, 10, 2);

        System.out.println("Pour water without edges， pour location start with lowest height");
        int[] heights3 = {3,2,1,2,2};
        pw.pourWaterWithNoEdge(heights3, 10, 2);
    }


}
