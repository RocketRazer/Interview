package a_lintcode.basic.wk7.sort_color_II;

public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0 || k == 1) {
            return;
        }

        partitionSort(colors, 0, colors.length - 1, 1, k);
    }


    private void partitionSort(int[] colors, int start, int end, int colorFrom, int colorTo) {

        if (start >= end)

        if (colorFrom == colorTo) {
            return;
        }

        int cMid = (colorTo - colorFrom) / 2 + colorFrom;

        int left = start, right = end;
        while (left <= right) {
            while (left <= right && colors[left] <= cMid) {
                left++;
            }

            while (left <= right && colors[right] > cMid) {
                right--;
            }

            if (left <= right) {
                int tmp =colors[left];
                colors[left] = colors[right];
                colors[right] = tmp;

                left++;
                right--;
            }
        }

        partitionSort(colors, start, left - 1, colorFrom, cMid);

        partitionSort(colors, left, end, cMid + 1, colorTo);


    }
}