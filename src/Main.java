import java.util.Arrays;

// "static void main" must be defined in a public class.
public class Main {

    public static int[] pourWater(int[] heights, int V, int K) {
        if (heights == null || heights.length == 0) {
            return new int[0];
        }

        int count  = V;
        int dropPoint = K;
        while (count > 0) {
            int dropPointHeight = heights[dropPoint];

            // check left side
            int left = dropPoint -1;
            int addIndex = -1;
            int lowest = dropPointHeight;
            while (left >= 0 && heights[left] <= lowest) {
                System.out.println("left Height: " + heights[left]);
                System.out.println("lowest: " + lowest);
                if (heights[left] < lowest) {
                    addIndex = left;
                    lowest = heights[left];
                }
                left--;
            }

            if (addIndex != -1) {
                heights[addIndex] += 1;
                count--;
                continue;
            }

            // check right side
            int right = dropPoint +1;
            addIndex = -1;
            lowest = dropPointHeight;
            while (right < heights.length && heights[right] <= lowest) {
                if (heights[right] < lowest) {
                    addIndex = right;
                    lowest = heights[right];
                }
                right++;
            }

            if (addIndex != -1) {
                heights[addIndex] += 1;
                count--;
                continue;
            }


            // no lower height on left or right side
            // add on current drop point
            heights[dropPoint]++;
            count--;

        }
        return heights;
    }

    public static void main(String[] args) {
        int[] height = {1,2,3,4,3,2,1,2,3,4,3,2,1};
        new String();
        System.out.print(Arrays.toString(pourWater(height, 5, 5)));
    }
}
