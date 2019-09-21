package airbnb;

import java.util.Arrays;

public class WiggleSort {
    public void wiggleSort(int[] nums) {
        if (nums.length <= 1) return;
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1])
                    || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(i, i - 1, nums);
            }
        }
    }

    private void swap(int a, int b, int[] nums) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }


    public static void main(String[] args) {
        WiggleSort ws = new WiggleSort();

        int[] nums = {3,7,6,1,5,8};
        ws.wiggleSort(nums);

        System.out.println(Arrays.toString(nums));
    }
}
